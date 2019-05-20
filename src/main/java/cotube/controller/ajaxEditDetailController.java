package cotube.controller;

import com.amazonaws.util.IOUtils;
import cotube.domain.*;
import cotube.services.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64.Decoder;
import java.util.List;

@Controller
@RequestMapping(value = "/editComicDetail.html")
public class ajaxEditDetailController {

    private String amazonURL =  "https://s3.amazonaws.com/cotubetest/";

    private AmazonS3ClientService amazonS3ClientService;
    @Autowired
    public void setAmazonS3ClientService(AmazonS3ClientService amazonS3ClientService) {
        this.amazonS3ClientService = amazonS3ClientService;
    }

    private ComicService comicService;
    @Autowired
    public void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }

    private RegularComicService regularComicService;
    @Autowired
    public void setRegularComicService(RegularComicService regularComicService) {
        this.regularComicService = regularComicService;
    }

    private FolderService folderService;
    @Autowired
    public void setFolderService(FolderService folderService){
        this.folderService = folderService;
    }
    private SeriesService seriesService;
    @Autowired
    public void setSeriesService(SeriesService seriesService){
        this.seriesService = seriesService;
    }

    private PanelService panelService;
    @Autowired
    public void setPanelService(PanelService panelService) {
        this.panelService = panelService;
    }

    private TagService tagService;
    @Autowired
    public void setTagService(TagService tagService) {this.tagService = tagService;}
    @RequestMapping(value = "/saveComic", method = RequestMethod.POST)
    public RedirectView saveComic(HttpServletRequest request) throws IOException {
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        String img = request.getParameter("data");
        byte[] imageByte;
        BufferedImage image = null;
        Decoder decoder = java.util.Base64.getMimeDecoder();


        imageByte = decoder.decode(img);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();

        String oldComicThumbnail = "newcomic-" + comicId + "_thumbnail.png";
        String oldSeriesName = "seriesnewcomic-" + comicId + "_thumbnail.png";
        String fileName = "newcomicID_" + comicId + ".png";
        File outputfile = new File("tmp/" + fileName); //file path and file name need to change
        File seriesfile = new File("tmp/" + oldSeriesName); //file path and file name need to change
        File cmcthmbfile = new File("tmp/" + oldComicThumbnail); //file path and file name need to change
        System.out.println(System.getProperty("user.dir"));
        //File outputfile = new File("src/main/resources/resources/img/t/4.jpg"); //file path and file name need to change
        ImageIO.write(image, "png", outputfile);
        ImageIO.write(image, "png", seriesfile);
        ImageIO.write(image, "png", cmcthmbfile);
        //File file = new File()
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
        MultipartFile multipartFile2 = new MultipartFile() {
            @Override
            public String getName() {
                return oldSeriesName;
            }

            @Override
            public String getOriginalFilename() {
                return oldSeriesName;
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
                return (int) seriesfile.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return IOUtils.toByteArray(new FileInputStream(seriesfile));            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(seriesfile);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        MultipartFile multipartFile3 = new MultipartFile() {
            @Override
            public String getName() {
                return oldComicThumbnail;
            }

            @Override
            public String getOriginalFilename() {
                return oldComicThumbnail;
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
                return (int) cmcthmbfile.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return IOUtils.toByteArray(new FileInputStream(cmcthmbfile));
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(cmcthmbfile);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };

        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile, true);
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile2, true);
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile3, true);

        return new RedirectView("?editComicId="+Integer.toString(comicId));

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String descr = request.getParameter("descr");
        String title = request.getParameter("title");
        String thumb = request.getParameter("thumb");
        String newSeries = request.getParameter("newSeries");
        String existSeries = request.getParameter("existSeries");
        String seriesThumb = request.getParameter("seriesThumb");
        String tag1 = request.getParameter("tag1");
        String tag2 = request.getParameter("tag2");
        String tag3 = request.getParameter("tag3");
        String tag4 = request.getParameter("tag4");
        String tag5 = request.getParameter("tag5");

        System.out.println("comicId:" + request.getParameter("comicId"));
        System.out.println("descr:" +request.getParameter("descr"));
        System.out.println("title:" +request.getParameter("title"));
        System.out.println("thumb:" +request.getParameter("thumb"));
        System.out.println("newSeries:" +request.getParameter("newSeries"));
        System.out.println("existSeries:" +request.getParameter("existSeries"));
        System.out.println("seriesThumb:" +request.getParameter("seriesThumb"));
        System.out.println("tag1:" +request.getParameter("tag1"));
        System.out.println("tag2:" +request.getParameter("tag2"));
        System.out.println("tag3:" +request.getParameter("tag3"));
        System.out.println("tag4:" +request.getParameter("tag4"));
        System.out.println("tag5:" +request.getParameter("tag5"));

        Comic comic = comicService.getComicByComic_Id(Integer.parseInt(comicId));
        comic.setTitle(title);
        comic.setStatus(0);
        RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(Integer.parseInt(comicId));
        rc.setDescription(descr);
        rc.setThumbnail_path(thumb);

        Panel panel = panelService.getPanelFromPanelId(rc.getPanel_id());
        panel.setCanvas_path(amazonURL + "comicID_" + comicId + ".png");

        //An existing series was selected
        if (!existSeries.equals("")){
            List<Series> seriesList = seriesService.getAllSeries();
            for (int i = 0; i < seriesList.size(); i++){
                if (seriesList.get(i).getSeries_name().equals(existSeries)) {
                    System.out.println("EXISTSER");
                    rc.setSeries_id(seriesList.get(i).getSeries_id());
                }
            }
        }
        //A new series was selected
        if (!newSeries.equals("")){
            String user = panel.getAuthor();
            Folder folder = new Folder();
            folder.setFolder_type(1);
            folder.setVisibility(1);
            folder.setUsername(user);
            folder.setFolder_name(user+newSeries+"seriesfolder");
            folderService.addFolder(folder);
            Series series = new Series();
            series.setFolder_id(folder.getFolder_id());
            series.setSeries_name(newSeries);
            series.setThumbnail_path(seriesThumb);
            seriesService.addSeries(series);
            rc.setSeries_id(series.getSeries_id());
        }

        regularComicService.addRegularComic(rc);

        List<Tag> previousTags = tagService.getAllTagsInRegularComic(Integer.parseInt(comicId));
        for (int i = 0; i < previousTags.size();i++){
            tagService.deleteTag(previousTags.get(i));
        }

        if (!tag1.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag1);
            tagService.addTag(tag);
        }
        if (!tag2.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag2);
            tagService.addTag(tag);
        }
        if (!tag3.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag3);
            tagService.addTag(tag);
        }
        if (!tag4.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag4);
            tagService.addTag(tag);
        }
        if (!tag5.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag5);
            tagService.addTag(tag);
        }

        //Replace comic panel
        String oldComicName = "newcomicID_" + comicId + ".png";
        String comicName = "comicID_" + comicId + ".png";
        URL url = new URL(amazonURL + oldComicName);
        BufferedImage img = ImageIO.read(url);
        File file = new File("tmp/image.png");
        ImageIO.write(img, "png", file);

        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return comicName;
            }

            @Override
            public String getOriginalFilename() {
                return comicName;
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
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldComicName);

        //Replace comic thumbnail
        String oldComicThumbnail = "newcomic-" + comicId + "_thumbnail.png";
        String comicThumbnail = "comic-" + comicId + "_thumbnail.png";
        URL url2 = new URL(amazonURL + oldComicThumbnail);
        BufferedImage img2 = ImageIO.read(url2);
        File file2 = new File("tmp/image2.png");
        ImageIO.write(img2, "png", file2);

        MultipartFile multipartFile2 = new MultipartFile() {
            @Override
            public String getName() {
                return comicThumbnail;
            }

            @Override
            public String getOriginalFilename() {
                return comicThumbnail;
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
                return (int) file2.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return IOUtils.toByteArray(new FileInputStream(file2));            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(file2);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        System.out.println("MultipartFile Name:" + multipartFile2.getName());
        System.out.println("MultipartFile OGName:" + multipartFile2.getOriginalFilename());
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile2, true);
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldComicThumbnail);

        //Replace series thumbnail
        String oldSeriesName = "seriesnewcomic-" + comicId + "_thumbnail.png";
        String seriesName = "seriescomic-" + comicId + "_thumbnail.png";
        URL url3 = new URL(amazonURL + oldSeriesName);
        BufferedImage img3 = ImageIO.read(url3);
        File file3 = new File("tmp/image3.png");
        ImageIO.write(img3, "png", file3);

        MultipartFile multipartFile3 = new MultipartFile() {
            @Override
            public String getName() {
                return seriesName;
            }

            @Override
            public String getOriginalFilename() {
                return seriesName;
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
                return (int) file3.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return IOUtils.toByteArray(new FileInputStream(file3));            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(file3);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile3, true);
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldSeriesName);

        comicService.addComic(comic);
        return "editComicDetail";
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public String publish(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String descr = request.getParameter("descr");
        String title = request.getParameter("title");
        String thumb = request.getParameter("thumb");
        String newSeries = request.getParameter("newSeries");
        String existSeries = request.getParameter("existSeries");
        String seriesThumb = request.getParameter("seriesThumb");
        String tag1 = request.getParameter("tag1");
        String tag2 = request.getParameter("tag2");
        String tag3 = request.getParameter("tag3");
        String tag4 = request.getParameter("tag4");
        String tag5 = request.getParameter("tag5");

        System.out.println("comicId:" + request.getParameter("comicId"));
        System.out.println("descr:" +request.getParameter("descr"));
        System.out.println("title:" +request.getParameter("title"));
        System.out.println("thumb:" +request.getParameter("thumb"));
        System.out.println("newSeries:" +request.getParameter("newSeries"));
        System.out.println("existSeries:" +request.getParameter("existSeries"));
        System.out.println("seriesThumb:" +request.getParameter("seriesThumb"));
        System.out.println("tag1:" +request.getParameter("tag1"));
        System.out.println("tag2:" +request.getParameter("tag2"));
        System.out.println("tag3:" +request.getParameter("tag3"));
        System.out.println("tag4:" +request.getParameter("tag4"));
        System.out.println("tag5:" +request.getParameter("tag5"));

        Comic comic = comicService.getComicByComic_Id(Integer.parseInt(comicId));
        comic.setTitle(title);
        comic.setStatus(3);
        RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(Integer.parseInt(comicId));
        rc.setDescription(descr);
        rc.setThumbnail_path(thumb);

        Panel panel = panelService.getPanelFromPanelId(rc.getPanel_id());
        panel.setCanvas_path(amazonURL + "comicID_" + comicId + ".png");

        //An existing series was selected
        if (!existSeries.equals("")){
            List<Series> seriesList = seriesService.getAllSeries();
            for (int i = 0; i < seriesList.size(); i++){
                if (seriesList.get(i).getSeries_name().equals(existSeries)) {
                    System.out.println("EXISTSER");
                    rc.setSeries_id(seriesList.get(i).getSeries_id());
                }
            }
        }
        //A new series was selected
        if (!newSeries.equals("")){
            String user = panel.getAuthor();
            Folder folder = new Folder();
            folder.setFolder_type(1);
            folder.setVisibility(1);
            folder.setUsername(user);
            folder.setFolder_name(user+newSeries+"seriesfolder");
            folderService.addFolder(folder);
            Series series = new Series();
            series.setFolder_id(folder.getFolder_id());
            series.setSeries_name(newSeries);
            series.setThumbnail_path(seriesThumb);
            seriesService.addSeries(series);
            rc.setSeries_id(series.getSeries_id());
        }

        regularComicService.addRegularComic(rc);

        List<Tag> previousTags = tagService.getAllTagsInRegularComic(Integer.parseInt(comicId));
        for (int i = 0; i < previousTags.size();i++){
            tagService.deleteTag(previousTags.get(i));
        }

        if (!tag1.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag1);
            tagService.addTag(tag);
        }
        if (!tag2.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag2);
            tagService.addTag(tag);
        }
        if (!tag3.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag3);
            tagService.addTag(tag);
        }
        if (!tag4.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag4);
            tagService.addTag(tag);
        }
        if (!tag5.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag5);
            tagService.addTag(tag);
        }

        //Replace comic panel
        String oldComicName = "newcomicID_" + comicId + ".png";
        String comicName = "comicID_" + comicId + ".png";
        URL url = new URL(amazonURL + oldComicName);
        BufferedImage img = ImageIO.read(url);
        File file = new File("tmp/image.png");
        ImageIO.write(img, "png", file);

        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return comicName;
            }

            @Override
            public String getOriginalFilename() {
                return comicName;
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
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldComicName);

        //Replace comic thumbnail
        String oldComicThumbnail = "newcomic-" + comicId + "_thumbnail.png";
        String comicThumbnail = "comic-" + comicId + "_thumbnail.png";
        URL url2 = new URL(amazonURL + oldComicThumbnail);
        BufferedImage img2 = ImageIO.read(url2);
        File file2 = new File("tmp/image2.png");
        ImageIO.write(img2, "png", file2);

        MultipartFile multipartFile2 = new MultipartFile() {
            @Override
            public String getName() {
                return comicThumbnail;
            }

            @Override
            public String getOriginalFilename() {
                return comicThumbnail;
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
                return (int) file2.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return IOUtils.toByteArray(new FileInputStream(file2));            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(file2);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        System.out.println("MultipartFile Name:" + multipartFile2.getName());
        System.out.println("MultipartFile OGName:" + multipartFile2.getOriginalFilename());
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile2, true);
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldComicThumbnail);

        //Replace series thumbnail
        String oldSeriesName = "seriesnewcomic-" + comicId + "_thumbnail.png";
        String seriesName = "seriescomic-" + comicId + "_thumbnail.png";
        URL url3 = new URL(amazonURL + oldSeriesName);
        BufferedImage img3 = ImageIO.read(url3);
        File file3 = new File("tmp/image3.png");
        ImageIO.write(img3, "png", file3);

        MultipartFile multipartFile3 = new MultipartFile() {
            @Override
            public String getName() {
                return seriesName;
            }

            @Override
            public String getOriginalFilename() {
                return seriesName;
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
                return (int) file3.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return IOUtils.toByteArray(new FileInputStream(file3));            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(file3);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile3, true);
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldSeriesName);

        comicService.addComic(comic);
        return "editComicDetail";
    }

    @RequestMapping(value = "/getComic", method = RequestMethod.POST)
    @ResponseBody
    public String getComic(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        List<Comic> comic = new ArrayList<>();
        comic.add(comicService.getComicByComic_Id(Integer.parseInt(comicId)));
        List<RegularComic> rc = new ArrayList<>();
        rc.add(regularComicService.getRegularComicByRegular_Comic_Id(Integer.parseInt(comicId)));
        List<Tag> tags = tagService.getAllTagsInRegularComic(Integer.parseInt(comicId));
        JSONObject result = new JSONObject();
        result.put("comic", comic);
        result.put("rc", rc);
        result.put("tags", tags);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value = "/getSeries", method = RequestMethod.POST)
    @ResponseBody
    public String getSeries(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        List<Folder> folderList = folderService.getAllFolders();
        List<Series> seriesList = seriesService.getAllSeries();
        List<Series> resultList = new ArrayList<>();
        for (int i = 0; i < seriesList.size(); i++){
            for(int j = 0; j < folderList.size(); j++){
                if (seriesList.get(i).getFolder_id().equals(folderList.get(j).getFolder_id()))
                    if (folderList.get(j).getUsername().equals(username))
                        resultList.add(seriesList.get(i));
            }
        }
        JSONObject result = new JSONObject();
        result.put("series", resultList);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value = "/uploadCmcThumb", method = RequestMethod.POST)
    @ResponseBody
    public String uploadCmcThumb(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String img = request.getParameter("img");
        System.out.println(img);
        String fileName= "newcomic-" + comicId + "_thumbnail.png";
        //String filePath = "./src/main/resources/resources/img/thumbnails/" + username + "_newProfilePicture.png";
        String filePath = "tmp/" + fileName;
        //String filePath = fileName;
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
        System.out.println("MultipartFile Name:" + multipartFile.getName());
        System.out.println("MultipartFile OGName:" + multipartFile.getOriginalFilename());
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile, true);

        return filePath;
    }

    @RequestMapping(value = "/uploadCmcThumbPub", method = RequestMethod.POST)
    @ResponseBody
    public String uploadCmcThumbPub(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String img = request.getParameter("img");
        System.out.println(img);
        String fileName= "newcomic-" + comicId + "_thumbnail.png";
        //String filePath = "./src/main/resources/resources/img/thumbnails/" + username + "_newProfilePicture.png";
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

    @RequestMapping(value = "/uploadSrsThumb", method = RequestMethod.POST)
    @ResponseBody
    public String uploadSrsThumb(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String img = request.getParameter("img");
        String filePath = "seriesnewcomic-" + comicId + "_thumbnail.png";
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
                return filePath;
            }

            @Override
            public String getOriginalFilename() {
                return filePath;
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

        return img;
    }

    @RequestMapping(value = "/pubpublish", method = RequestMethod.POST)
    public String pubPublish(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String descr = request.getParameter("descr");
        String title = request.getParameter("title");
        String thumb = request.getParameter("thumb");
        String tag1 = request.getParameter("tag1");
        String tag2 = request.getParameter("tag2");
        String tag3 = request.getParameter("tag3");
        String tag4 = request.getParameter("tag4");
        String tag5 = request.getParameter("tag5");

        System.out.println("comicId:" + request.getParameter("comicId"));
        System.out.println("descr:" +request.getParameter("descr"));
        System.out.println("title:" +request.getParameter("title"));
        System.out.println("thumb:" +request.getParameter("thumb"));
        System.out.println("tag1:" +request.getParameter("tag1"));
        System.out.println("tag2:" +request.getParameter("tag2"));
        System.out.println("tag3:" +request.getParameter("tag3"));
        System.out.println("tag4:" +request.getParameter("tag4"));
        System.out.println("tag5:" +request.getParameter("tag5"));

        Comic comic = comicService.getComicByComic_Id(Integer.parseInt(comicId));
        comic.setTitle(title);
        comic.setStatus(3);
        RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(Integer.parseInt(comicId));
        rc.setDescription(descr);
        rc.setThumbnail_path(thumb);

        Panel panel = panelService.getPanelFromPanelId(rc.getPanel_id());
        panel.setCanvas_path(amazonURL + "comicID_" + comicId + ".png");



        regularComicService.addRegularComic(rc);

        List<Tag> previousTags = tagService.getAllTagsInRegularComic(Integer.parseInt(comicId));
        for (int i = 0; i < previousTags.size();i++){
            tagService.deleteTag(previousTags.get(i));
        }

        if (!tag1.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag1);
            tagService.addTag(tag);
        }
        if (!tag2.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag2);
            tagService.addTag(tag);
        }
        if (!tag3.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag3);
            tagService.addTag(tag);
        }
        if (!tag4.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag4);
            tagService.addTag(tag);
        }
        if (!tag5.equals("")) {
            Tag tag = new Tag();
            tag.setRegular_comic_id(Integer.parseInt(comicId));
            tag.setComic_tag(tag5);
            tagService.addTag(tag);
        }

        //Replace comic panel
        String oldComicName = "newcomicID_" + comicId + ".png";
        String comicName = "comicID_" + comicId + ".png";
        URL url = new URL(amazonURL + oldComicName);
        BufferedImage img = ImageIO.read(url);
        File file = new File("tmp/image.png");
        ImageIO.write(img, "png", file);

        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return comicName;
            }

            @Override
            public String getOriginalFilename() {
                return comicName;
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
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldComicName);

        //Replace comic thumbnail
        String oldComicThumbnail = "newcomic-" + comicId + "_thumbnail.png";
        String comicThumbnail = "comic-" + comicId + "_thumbnail.png";
        URL url2 = new URL(amazonURL + oldComicThumbnail);
        BufferedImage img2 = ImageIO.read(url2);
        File file2 = new File("tmp/image2.png");
        ImageIO.write(img2, "png", file2);

        MultipartFile multipartFile2 = new MultipartFile() {
            @Override
            public String getName() {
                return comicThumbnail;
            }

            @Override
            public String getOriginalFilename() {
                return comicThumbnail;
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
                return (int) file2.length();
            }

            @Override
            public byte[] getBytes() throws IOException {
                return IOUtils.toByteArray(new FileInputStream(file2));            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(file2);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        System.out.println("MultipartFile Name:" + multipartFile2.getName());
        System.out.println("MultipartFile OGName:" + multipartFile2.getOriginalFilename());
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile2, true);
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldComicThumbnail);

        comicService.addComic(comic);
        return "editComicDetail";
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String cancel(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        System.out.println("comicId:" + request.getParameter("comicId"));

        String oldComicThumbnail = "newcomic-" + comicId + "_thumbnail.png";
        String oldSeriesName = "seriesnewcomic-" + comicId + "_thumbnail.png";
        String oldComicName = "newcomicID_" + comicId + ".png";
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldSeriesName);
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldComicName);
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldComicThumbnail);

        return "editComicDetail";
    }

    @RequestMapping(value = "/pubCancel", method = RequestMethod.POST)
    public String pubCancel(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        System.out.println("comicId:" + request.getParameter("comicId"));
        String oldComicName = "newcomicID_" + comicId + ".png";
        String oldComicThumbnail = "newcomic-" + comicId + "_thumbnail.png";
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldComicName);
        this.amazonS3ClientService.deleteFileFromS3Bucket(oldComicThumbnail);
        return "editComicDetail";
    }
    }
