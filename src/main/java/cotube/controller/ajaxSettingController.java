package cotube.controller;

import com.amazonaws.util.IOUtils;
import cotube.domain.Account;
import cotube.services.AccountService;
import cotube.services.AmazonS3ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Base64.Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/setting.html")
public class ajaxSettingController {

    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private String amazonURL =  "https://s3.amazonaws.com/cotubetest/";

    private AmazonS3ClientService amazonS3ClientService;
    @Autowired
    public void setAmazonS3ClientService(AmazonS3ClientService amazonS3ClientService) {
        this.amazonS3ClientService = amazonS3ClientService;
    }


    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateResetPassword(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("new_password");
        Account current = this.accountService.getAccountByUsername(username);
        if (current.getPassword().equals(password)) {
            current.setPassword(newPassword);
            this.accountService.addAccount(current);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/resetQuestion", method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateResetQuestion(HttpServletRequest request) {
        String username = request.getParameter("username");
        Integer originalQuestion = Integer.parseInt(request.getParameter("original_question"));
        String originalAnswer = request.getParameter("original_answer");
        Integer newQuestion = Integer.parseInt(request.getParameter("new_question"));
        String newAnswer = request.getParameter("new_answer");

        Account current = this.accountService.getAccountByUsername(username);
        if (current.getSecurity_question().equals(originalQuestion)) {
            if (current.getSecurity_answer().equals(originalAnswer)) {
                current.setSecurity_question(newQuestion);
                current.setSecurity_answer(newAnswer);
                this.accountService.addAccount(current);
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
    @ResponseBody
    public String uploadPicture(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        String img = request.getParameter("img");
        String fileName =  username + "_newProfilePicture.png";
        String filePath = "tmp/" + fileName;
        //File path and need to change
        byte[] imageByte;
        BufferedImage image = null;
        Decoder decoder = java.util.Base64.getMimeDecoder();

        imageByte = decoder.decode(img);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();
        
        System.out.println(filePath);
        File outputfile = new File(filePath);
        ImageIO.write(image, "png", outputfile);
        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return fileName;
            }

            @Override
            public String getOriginalFilename() {
                return fileName;
            }

            @Override
            public String getContentType() {
                return "images/png";
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return (int) outputfile.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return IOUtils.toByteArray(new FileInputStream(outputfile));            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(outputfile);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile, true);

        return filePath;
    }

    @RequestMapping(value = "/loadProfile", method = RequestMethod.POST)
    @ResponseBody
    public String loadProfilePic(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        Account changed = this.accountService.getAccountByUsername(username);
        return changed.getProfile_pic_path();
    }
    @RequestMapping(value="/changeProfile",method = RequestMethod.POST)
    @ResponseBody
    public String setProfilePicture(HttpServletRequest request) throws IOException{
        // Update db here!!!!
        // Replace the old profile picture file
        // Delete the username_newProfilePicture.png
        // Return true if success else false
        String username = request.getParameter("username");
        String img = request.getParameter("img");
        Account changed = this.accountService.getAccountByUsername(username);
        String oldPP = username + "_newProfilePicture.png";
        String PP = username + "_ProfilePicture.png";
        URL url = new URL(amazonURL + oldPP);
        BufferedImage imag = ImageIO.read(url);
        File file = new File("tmp/image.png");
        ImageIO.write(imag, "png", file);

        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return PP;
            }

            @Override
            public String getOriginalFilename() {
                return PP;
            }

            @Override
            public String getContentType() {
                return "images/png";
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return (int) file.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return IOUtils.toByteArray(new FileInputStream(file));            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(file);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        System.out.println("MultipartFile Name:" + multipartFile.getName());
        System.out.println("MultipartFile OGName:" + multipartFile.getOriginalFilename());
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile, true);
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldPP);

        changed.setProfile_pic_path(amazonURL + PP);
        this.accountService.addAccount(changed);
        return (changed.getProfile_pic_path());
    }
}
