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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Base64.Decoder;
import java.util.List;

@Controller
@RequestMapping(value = "/createComicDetail.html")
public class ajaxCreateDetailController {

    private String amazonURL =  "https://s3.amazonaws.com/cotubetest/";

    private AmazonS3ClientService amazonS3ClientService;
    @Autowired
    public void setAmazonS3ClientService(AmazonS3ClientService amazonS3ClientService) {
        this.amazonS3ClientService = amazonS3ClientService;
    }

    private NotificationService notificationService;
    @Autowired
    public void setNotificationService(NotificationService notificationService){
        this.notificationService = notificationService;
    }
    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private FollowUserService followUserService;
    @Autowired
    public void setFollowUserService(FollowUserService followUserService) {
        this.followUserService = followUserService;
    }

    private FolderService folderService;
    @Autowired
    public void setFolderService(FolderService folderService) {
        this.folderService = folderService;
    }

    private FollowSeriesService followSeriesService;
    @Autowired
    public void setFollowSeriesService(FollowSeriesService followSeriesService) {
        this.followSeriesService = followSeriesService;
    }

    private SeriesService seriesService;
    @Autowired
    public void setSeriesService(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    private FavoriteService favoriteService;
    @Autowired
    public void setFavoriteService(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    private ComicService comicService;
    @Autowired
    public void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }

    private CommentsService commentsService;
    @Autowired
    public void setCommentsService(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    private RegularComicService regularComicService;
    @Autowired
    public void setRegularComicService(RegularComicService regularComicService) {
        this.regularComicService = regularComicService;
    }

    private PanelService panelService;
    @Autowired
    public void setPanelService(PanelService panelService) {
        this.panelService = panelService;
    }

    private ViewsService viewsService;
    @Autowired
    public void setViewsService(ViewsService viewsService) {
        this.viewsService = viewsService;
    }

    private LikesService likesService;
    @Autowired
    public void setLikesService(LikesService likesService) {
        this.likesService = likesService;
    }

    private GameComicService gameComicService;
    @Autowired
    public void setGameComicService(GameComicService gameComicService){
        this.gameComicService = gameComicService;
    }

    private TagService tagService;
    @Autowired
    public void setTagService(TagService tagService) {this.tagService = tagService;}

    @RequestMapping(value = "/saveComic", method = RequestMethod.POST)
    public RedirectView saveComic(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        String img = request.getParameter("data");
        byte[] imageByte;
        BufferedImage image = null;
        Decoder decoder = java.util.Base64.getMimeDecoder();
        //Integer comicId = 23333; //comidId which need to return

        imageByte = decoder.decode(img);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();
/*
        File outputfile = new File("cotubeImage.png"); //file path and file name need to change
        ImageIO.write(image, "png", outputfile);
        System.out.println(request.getParameter("username"));
*/
        /*

            Create a new comic, regularComic and panel in the db with the username given
            Fill the other fields of the tables with placeholder
            Save the newly generated comicId in the variable comidId

        */

        Panel panel = new Panel();
        Comic comic = new Comic();
        RegularComic rc = new RegularComic();
        panel.setAuthor(username);
        comic.setComic_type(0);
        comic.setStatus(0);
        comicService.addComic(comic);
        Integer comicId = comic.getComic_id();
        panel.setCanvas_path("comicID_" + comicId + ".png");
        panelService.addPanel(panel);
        System.out.println(panel.getCanvas_path());
        rc.setRegular_comic_id(comicId);
        rc.setPanel_id(panel.getPanel_id());

        //comicService.addComic(comic);
        regularComicService.addRegularComic(rc);
        String oldComicThumbnail = "comic-" + comicId + "_thumbnail.png";
        String oldSeriesName = "seriescomic-" + comicId + "_thumbnail.png";
        String fileName = "comicID_" + comicId + ".png";
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

        System.out.println("MultipartFile Name:" + multipartFile.getName());
        System.out.println("MultipartFile OGName:" + multipartFile.getOriginalFilename());
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile, true);
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile2, true);
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile3, true);

        return new RedirectView("?createComicId="+Integer.toString(comicId));
    }

    @RequestMapping(value = "/uploadCmcThumb", method = RequestMethod.POST)
    @ResponseBody
    public String uploadCmcThumb(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String img = request.getParameter("img");
        System.out.println(img);
        String fileName= "comic-" + comicId + "_thumbnail.png";
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
        System.out.println("MultipartFile Name:" + multipartFile.getName());
        System.out.println("MultipartFile OGName:" + multipartFile.getOriginalFilename());
        this.amazonS3ClientService.uploadMultipartFileToS3Bucket(multipartFile, true);

        return filePath;
    }

    @RequestMapping(value = "/uploadSrsThumb", method = RequestMethod.POST)
    @ResponseBody
    public String uploadSrsThumb(HttpServletRequest request) throws IOException {
        String comicId = request.getParameter("comicId");
        String img = request.getParameter("img");
        String fileName= "seriescomic-" + comicId + "_thumbnail.png";
        //File path and need to change
        String filePath = "./tmp/" + fileName;
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

        return img;
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

        comicService.addComic(comic);
        return "createComicDetail";
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
            System.out.println("picknew");
            String user = panelService.getPanelFromPanelId(rc.getPanel_id()).getAuthor();
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

        comicService.addComic(comic);
        return "createComicDetail";
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
            if (seriesList.get(i).getFolder_id().equals( folderList.get(j).getFolder_id()))
                if (folderList.get(j).getUsername().equals(username))
                    resultList.add(seriesList.get(i));
            }
        }
        JSONObject result = new JSONObject();
        result.put("series", resultList);
        System.out.println(result.toString());

        String test = request.getRequestURI();
        System.out.println(test);
        return result.toString();
    }

    @RequestMapping(value="/cancel",method = RequestMethod.POST)
    @ResponseBody
    public Boolean cancel(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        Comic comic = comicService.getComicByComic_Id(comicId);
        int type = comic.getComic_type();
        if (type == 0) {//regular

            //NOTIFICATION SECTION
            List <Favorite> allFavorites = this.favoriteService.getAllFavorites();
            for(Favorite fav: allFavorites){
                if (fav.getComic_id().equals( comicId)){
                    Date now = new Date();
                    int notification_type = 4;
                    String notification = "Favorite comic " + comic.getTitle() + " was deleted";
                    Notification note = new Notification();
                    note.setNotification_type(notification_type);
                    note.setNotification(notification);
                    note.setUsername(fav.getFavoriter_username());
                    note.setNotifcation_time(now);
                    this.notificationService.addNotification(note);
                }
            }
            //END NOTIFICATION SECTION

            RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(comicId);
            Integer series_id = rc.getSeries_id();

            //delete From Tag
            Integer regular_comic_id = comicId;
            List<Tag> tagList = tagService.getAllTagsInRegularComic(regular_comic_id);
            for (int i = 0; i < tagList.size(); i++)
                tagService.deleteTag(tagList.get(i));

            //delete From Views
            List<Views> viewsList = viewsService.getAllViewsInComic(comicId);
            for (int i = 0; i < viewsList.size(); i++)
                viewsService.deleteView(viewsList.get(i));

            //delete from Likes
            List<Likes> likesList = likesService.getAllLikesInComic(comicId);
            for (int i = 0; i < likesList.size(); i++)
                likesService.deleteLike(likesList.get(i));

            //delete from Comments
            List<Comments> commentsList = commentsService.getAllCommentsInComic(comicId);
            for (int i = 0; i < commentsList.size(); i++)
                commentsService.deleteComment(commentsList.get(i));

            //delete from Favorites
            List<Favorite> favoritesList = favoriteService.getAllFavoritesInComic(comicId);
            for (int i = 0; i < favoritesList.size(); i++)
                favoriteService.deleteFavorite(favoritesList.get(i));

            //delete from RegularComic
            regularComicService.deleteRegularComic(rc);

            //delete From Panel
            panelService.deletePanel(panelService.getPanelFromPanelId(rc.getPanel_id()));

            //delete from Comic
            comicService.deleteComic(comic);

            if (series_id != null) {
                List<RegularComic> rcSeriesList = regularComicService.getAllRegularComicsInSeries(series_id);
                if(rcSeriesList.isEmpty()){

                    //delete from FollowSeries
                    List<FollowSeries> followSeriesList = followSeriesService.getAllFollowSeriesInSeries(series_id);
                    for (int i = 0; i < followSeriesList.size(); i++)
                        followSeriesService.deleteFollowSeries(followSeriesList.get(i));

                    //delete from Series
                    seriesService.deleteSeries(seriesService.getSeriesBySeriesId(series_id));

                }
            }
        }
        return false;
    }

}


