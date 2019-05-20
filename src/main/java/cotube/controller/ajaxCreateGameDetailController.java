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
import java.util.Base64.Decoder;
import java.util.List;

@Controller
@RequestMapping(value = "/createGameDetail.html")
public class ajaxCreateGameDetailController {

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

    private GameComicService gameComicService;
    @Autowired
    public void setGameComicService(GameComicService gameComicService) {
        this.gameComicService = gameComicService;
    }

    private NotificationService notificationService;
    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    private PanelService panelService;
    @Autowired
    public void setPanelService(PanelService panelService) {
        this.panelService = panelService;
    }

    
    @RequestMapping(value = "/saveComic", method = RequestMethod.POST)
    public RedirectView saveComic(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        String img = request.getParameter("data");
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        Integer panelNo = Integer.parseInt(request.getParameter("panelNo"));

        /*
            Save the picture which is stored in variable 'img'
            May update the canvas_path in the panel 
        */

        byte[] imageByte;
        BufferedImage image = null;
        Decoder decoder = java.util.Base64.getMimeDecoder();
        //Integer comicId = 23333; //comidId which need to return

        imageByte = decoder.decode(img);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();

        Panel panel = null;
        if (panelNo == 1)
            panel = panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(comicId).getPanel1_id());
        if (panelNo == 2)
            panel = panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(comicId).getPanel2_id());
        if (panelNo == 3)
            panel = panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(comicId).getPanel3_id());
        if (panelNo == 4)
            panel = panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(comicId).getPanel4_id());
        String fileName = "gc_" + comicId + "_panelID_" + panel.getPanel_id() + ".png";
        File outputfile = new File("tmp/" + fileName); //file path and file name need to change
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

        System.out.println(panel);
        System.out.println(fileName);
        System.out.println(username);
        System.out.println(comicId);
        System.out.println(panelNo);

        return new RedirectView("?comicId="+Integer.toString(comicId));// return gameId
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public Boolean submitGame(HttpServletRequest request) {
        String username = request.getParameter("username");
        String titleWord = request.getParameter("titleWord");
        Integer gameId = Integer.parseInt(request.getParameter("comicId"));
        Integer panelNo = Integer.parseInt(request.getParameter("panelNo"));
        System.out.println("GAME ID SUBMIT: " + gameId);
        System.out.println("PANEL IN SUBMIT: " + panelNo);
        GameComic gc = gameComicService.getGameComicByGameComicId(gameId);
        boolean exists = false;

        if (panelNo == 1){
            Panel panel1 = panelService.getPanelFromPanelId(gc.getPanel1_id());
            if(!panel1.getAuthor().equals(username))
                return exists;
            else {
                panel1.setTitle_word(titleWord);
                String fileName = "gc_" + gameId + "_panelID_" + panel1.getPanel_id() + ".png";
                panel1.setCanvas_path(amazonURL + fileName);
                panelService.addPanel(panel1);
            }
        }
        if (panelNo == 2){
            Panel panel2 = panelService.getPanelFromPanelId(gc.getPanel2_id());
            if(!panel2.getAuthor().equals(username))
                return exists;
            else {
                panel2.setTitle_word(titleWord);
                String fileName = "gc_" + gameId + "_panelID_" + panel2.getPanel_id() + ".png";
                panel2.setCanvas_path(amazonURL + fileName);
                panelService.addPanel(panel2);
            }
        }
        if (panelNo == 3){
            Panel panel3 = panelService.getPanelFromPanelId(gc.getPanel3_id());
            if(!panel3.getAuthor().equals(username))
                return exists;
            else {
                panel3.setTitle_word(titleWord);
                String fileName = "gc_" + gameId + "_panelID_" + panel3.getPanel_id() + ".png";
                panel3.setCanvas_path(amazonURL + fileName);
                panelService.addPanel(panel3);
            }
        }
        if (panelNo == 4){
            Panel panel4 = panelService.getPanelFromPanelId(gc.getPanel4_id());
            if(!panel4.getAuthor().equals(username))
                return exists;
            else {
                panel4.setTitle_word(titleWord);
                String fileName = "gc_" + gameId + "_panelID_" + panel4.getPanel_id() + ".png";
                panel4.setCanvas_path(amazonURL + fileName);
                panelService.addPanel(panel4);
            }
        }

        String panel1title = "";
        String panel2title = "";
        String panel3title = "";
        String panel4title = "";

        if (gc.getPanel1_id() != null)
            if (panelService.getPanelFromPanelId(gc.getPanel1_id()).getCanvas_path() != null)
                panel1title = panelService.getPanelFromPanelId(gc.getPanel1_id()).getTitle_word();

        if (gc.getPanel2_id() != null)
            if (panelService.getPanelFromPanelId(gc.getPanel2_id()).getCanvas_path() != null)
                panel2title = panelService.getPanelFromPanelId(gc.getPanel2_id()).getTitle_word();

        if (gc.getPanel3_id() != null)
            if (panelService.getPanelFromPanelId(gc.getPanel3_id()).getCanvas_path() != null)
                panel3title = panelService.getPanelFromPanelId(gc.getPanel3_id()).getTitle_word();

        if (gc.getPanel4_id() != null)
            if (panelService.getPanelFromPanelId(gc.getPanel4_id()).getCanvas_path() != null)
                panel4title = panelService.getPanelFromPanelId(gc.getPanel4_id()).getTitle_word();

        Comic comic = comicService.getComicByComic_Id(gc.getGame_comic_id());
        comic.setTitle(panel1title + " " + panel2title + " " + panel3title + " " + panel4title);
        comicService.addComic(comic);

        if (gc.getGamecomic_type() == 1){
            if (panelNo != 1){
                List<Notification> notifications = notificationService.getAllNotifications();
                System.out.println("COND REACHED");
                for(int i = 0; i < notifications.size(); i++){
                    if (notifications.get(i).getUsername().equals(username) && notifications.get(i).getNotification_type() == 6){
                        String[] arrOfStr = notifications.get(i).getLink().split(" ");
                        Integer id = Integer.parseInt(arrOfStr[0]);
                                if (id .equals( gameId)) {
                                    System.out.println(notifications.get(i).getNotification());
                                    notificationService.deleteNotification(notifications.get(i));
                        }
                    }
                }
            }
        }
        if (gc.getPanel1_id() != null && gc.getPanel2_id() != null && gc.getPanel3_id() != null && gc.getPanel4_id() != null) {
            Panel panel1 = panelService.getPanelFromPanelId(gc.getPanel1_id());
            Panel panel2 = panelService.getPanelFromPanelId(gc.getPanel2_id());
            Panel panel3 = panelService.getPanelFromPanelId(gc.getPanel3_id());
            Panel panel4 = panelService.getPanelFromPanelId(gc.getPanel4_id());
            if (panel1.getCanvas_path() != null && panel2.getCanvas_path() != null && panel3.getCanvas_path() != null && panel4.getCanvas_path() != null) {
                comic.setStatus(3);
                comicService.addComic(comic);

                Notification note = new Notification();
                note.setNotification_type(7);
                String notification = "Game comic with title " + comic.getTitle() + " is finished";
                note.setNotification(notification);
                note.setUsername(panel1.getAuthor());
                note.setLink(gameId + "");
                notificationService.addNotification(note);

                Notification note2 = new Notification();
                note2.setNotification_type(7);
                note2.setNotification(notification);
                note2.setUsername(panel2.getAuthor());
                note2.setLink(gameId + "");
                notificationService.addNotification(note2);

                Notification note3 = new Notification();
                note3.setNotification_type(7);
                note3.setNotification(notification);
                note3.setUsername(panel3.getAuthor());
                note3.setLink(gameId + "");
                notificationService.addNotification(note3);

                Notification note4 = new Notification();
                note4.setNotification_type(7);
                note4.setNotification(notification);
                note4.setUsername(panel4.getAuthor());
                note4.setLink(gameId + "");
                notificationService.addNotification(note4);
            }
        }
        /*
            Check if the user is still in the game
            if not{
                return false
            }
            else{
                update canvas_path and titleWord to the database
                if the game comic completes(all 4 panels are complete){
                    Notify all the 4 users
                }
                return true
                DONE
            }
        */

        return true;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public Boolean cancelGame(HttpServletRequest request) {
        System.out.println("CANCEL WAS CALLED");
        String username = request.getParameter("username");
        Integer gameId = Integer.parseInt(request.getParameter("comicId"));
        Integer panelNo = Integer.parseInt(request.getParameter("panelNo"));

        /*
            Check if the user is still in the game
            if not{
                return true directly
            }
            else public{
                delete the panel he created
                remove the user from the game in the database
                if the game is empty delete the game
                return true   
            }
              else private{
                remove canvas_path of the panel in the database
                DO NOT!!!!remove the user from the game in the database
                return true
            }

        */
        GameComic gc = gameComicService.getGameComicByGameComicId(gameId);

        if (panelNo == 1){
            Panel panel1 = panelService.getPanelFromPanelId(gc.getPanel1_id());
            if(!panel1.getAuthor().equals(username))
                return true;
            else {
                if (gc.getGamecomic_type() == 1) {
                    String fileName = "gc_" + gameId + "_panelID_" + panel1.getPanel_id() + ".png";
                    this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);
                    panel1.setCanvas_path(null);
                    panel1.setTitle_word(null);
                    panelService.addPanel(panel1);
                }
                else{
                    String fileName = "gc_" + gameId + "_panelID_" + panel1.getPanel_id() + ".png";
                    this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);
                    gc.setPanel1_id(null);
                    gameComicService.addGameComic(gc);
                    panelService.deletePanel(panel1);
                }
            }
        }
        if (panelNo == 2){
            Panel panel2 = panelService.getPanelFromPanelId(gc.getPanel2_id());
            if(!panel2.getAuthor().equals(username))
                return true;
            else {
                if (gc.getGamecomic_type() == 1) {
                    String fileName = "gc_" + gameId + "_panelID_" + panel2.getPanel_id() + ".png";
                    this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);
                    panel2.setCanvas_path(null);
                    panel2.setTitle_word(null);
                    panelService.addPanel(panel2);
                }
                else{
                    String fileName = "gc_" + gameId + "_panelID_" + panel2.getPanel_id() + ".png";
                    this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);
                    gc.setPanel2_id(null);
                    gameComicService.addGameComic(gc);
                    panelService.deletePanel(panel2);
                }

            }
        }
        if (panelNo == 3){
            Panel panel3 = panelService.getPanelFromPanelId(gc.getPanel3_id());
            if(!panel3.getAuthor().equals(username))
                return true;
            else {
                if (gc.getGamecomic_type() == 1) {
                    String fileName = "gc_" + gameId + "_panelID_" + panel3.getPanel_id() + ".png";
                    this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);
                    panel3.setCanvas_path(null);
                    panel3.setTitle_word(null);
                    panelService.addPanel(panel3);
                }
                else{
                    String fileName = "gc_" + gameId + "_panelID_" + panel3.getPanel_id() + ".png";
                    this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);
                    gc.setPanel3_id(null);
                    gameComicService.addGameComic(gc);
                    panelService.deletePanel(panel3);
                }

            }
        }
        if (panelNo == 4){
            Panel panel4 = panelService.getPanelFromPanelId(gc.getPanel4_id());
            if(!panel4.getAuthor().equals(username))
                return true;
            else {
                if (gc.getGamecomic_type() == 1) {
                    String fileName = "gc_" + gameId + "_panelID_" + panel4.getPanel_id() + ".png";
                    this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);
                    panel4.setCanvas_path(null);
                    panel4.setTitle_word(null);
                    panelService.addPanel(panel4);
                }
                else{
                    String fileName = "gc_" + gameId + "_panelID_" + panel4.getPanel_id() + ".png";
                    this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);
                    gc.setPanel4_id(null);
                    gameComicService.addGameComic(gc);
                    panelService.deletePanel(panel4);
                }
            }
        }

        if (gc.getGamecomic_type() != 1) {
            if (checkEmptyGame(gameId)) {
                Panel panel1 = panelService.getPanelFromPanelId(gc.getPanel1_id());
                Panel panel2 = panelService.getPanelFromPanelId(gc.getPanel2_id());
                Panel panel3 = panelService.getPanelFromPanelId(gc.getPanel3_id());
                Panel panel4 = panelService.getPanelFromPanelId(gc.getPanel4_id());
                gameComicService.deleteGameComic(gc);
                comicService.deleteComic(comicService.getComicByComic_Id(gameId));
                if (panel1 != null)
                    panelService.deletePanel(panel1);
                if (panel2 != null)
                    panelService.deletePanel(panel2);
                if (panel3 != null)
                    panelService.deletePanel(panel3);
                if (panel4 != null)
                    panelService.deletePanel(panel4);
            }
        }
        return true;
    }


    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    @ResponseBody
    public String getInfo(HttpServletRequest request) throws IOException {
        String gameID = request.getParameter("gameId");
        String cur = request.getParameter("current");
        Integer gameid = Integer.parseInt(gameID);
        Integer currentPanel = Integer.parseInt(cur);
        GameComic gc = gameComicService.getGameComicByGameComicId(gameid);
        List<String> path = new ArrayList<>();
        List<String> word = new ArrayList<>();
        List<String> num = new ArrayList<>();
        String keyword = gc.getKeyword();
        Panel panel = null;
        if(currentPanel==1){
            panel = panelService.getPanelFromPanelId(gc.getPanel1_id());
            num.add("this");
            path.add(null);
            word.add(null);
        }else{
            num.add("No.1");
            if(gc.getPanel1_id()==null){
                path.add(null);
                word.add(null);
            }else{
                Panel temp = panelService.getPanelFromPanelId(gc.getPanel1_id());
                path.add(temp.getCanvas_path());
                word.add(temp.getTitle_word());
            }
        }
        if(currentPanel==2){
            panel = panelService.getPanelFromPanelId(gc.getPanel2_id());
            num.add("this");
            path.add(null);
            word.add(null);
        }else{
            num.add("No.2");
            if(gc.getPanel2_id()==null){
                path.add(null);
                word.add(null);
            }else{
                Panel temp = panelService.getPanelFromPanelId(gc.getPanel2_id());
                path.add(temp.getCanvas_path());
                word.add(temp.getTitle_word());
            }
        }
        if(currentPanel==3){
            panel = panelService.getPanelFromPanelId(gc.getPanel3_id());
            num.add("this");
            path.add(null);
            word.add(null);
        }else{
            num.add("No.3");
            if(gc.getPanel3_id()==null){
                path.add(null);
                word.add(null);
            }else{
                Panel temp = panelService.getPanelFromPanelId(gc.getPanel3_id());
                path.add(temp.getCanvas_path());
                word.add(temp.getTitle_word());
            }
        }
        if(currentPanel==4){
            panel = panelService.getPanelFromPanelId(gc.getPanel4_id());
            num.add("this");
            path.add(null);
            word.add(null);
        }else{
            num.add("No.4");
            if(gc.getPanel4_id()==null){
                path.add(null);
                word.add(null);
            }else{
                Panel temp = panelService.getPanelFromPanelId(gc.getPanel4_id());
                path.add(temp.getCanvas_path());
                word.add(temp.getTitle_word());
            }
        }
        JSONObject result = new JSONObject();
        String fileName = "gc_" + gc.getGame_comic_id() + "_panelID_" + panel.getPanel_id() + ".png";
        result.put("pic", amazonURL +  fileName);
        result.put("num", num);
        result.put("path", path);
        result.put("word", word);
        result.put("keyword", keyword);
        System.out.println(result.toString());
        return result.toString();
    }

    public Boolean checkEmptyGame(Integer gameId) {
        GameComic gc = gameComicService.getGameComicByGameComicId(gameId);
        Boolean panel1Empty = true;
        Boolean panel2Empty = true;
        Boolean panel3Empty = true;
        Boolean panel4Empty = true;

        if (gameComicService.getGameComicByGameComicId(gameId).getPanel1_id() != null) {
            if (panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(gameId).getPanel1_id()).getAuthor() != null)
                panel1Empty = false;
        }
        if (gameComicService.getGameComicByGameComicId(gameId).getPanel2_id() != null) {
            if (panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(gameId).getPanel2_id()).getAuthor() != null)
                panel2Empty = false;
        }
        if (gameComicService.getGameComicByGameComicId(gameId).getPanel3_id() != null) {
            if (panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(gameId).getPanel3_id()).getAuthor() != null)
                panel3Empty = false;
        }
        if (gameComicService.getGameComicByGameComicId(gameId).getPanel4_id() != null) {
            if (panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(gameId).getPanel4_id()).getAuthor() != null)
                panel4Empty = false;
        }

        if (panel1Empty && panel2Empty && panel3Empty && panel4Empty)
            return true;
        else
            return false;
    }

}
