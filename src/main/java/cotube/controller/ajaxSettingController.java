package cotube.controller;

import cotube.domain.Account;
import cotube.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
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
        //String filePath = "./src/main/resources/resources/img/thumbnails/" + username + "_newProfilePicture.png";
        String filePath = "./src/main/resources/resources/img/" + username + "_newProfilePicture.png";
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
    public Boolean setProfilePicture(HttpServletRequest request){
        // Update db here!!!!
        // Replace the old profile picture file
        // Delete the username_newProfilePicture.png
        // Return true if success else false
        String username = request.getParameter("username");
        String img = request.getParameter("img");
        Account changed = this.accountService.getAccountByUsername(username);
        changed.setProfile_pic_path(img);
        this.accountService.addAccount(changed);
        return true;
    }
}
