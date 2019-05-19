
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
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/createGame.html")
public class ajaxCreateGameController {

    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
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

    private PanelService panelService;
    @Autowired
    public void setPanelService(PanelService panelService) {
        this.panelService = panelService;
    }

    private KeywordService keywordService;
    @Autowired
    public void setKeywordService(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    private NotificationService notificationService;
    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @RequestMapping(value = "/playRandom", method = RequestMethod.POST)
    @ResponseBody
    public String playRandom(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        Integer gameId = 0;
        Integer panelNo = 0;
        Random rand = new Random();
        List<GameComic> gcs = gameComicService.getAllGameComics();
        List<Integer> availableIds = new ArrayList<>();

        for(int i = 0; i < gcs.size(); i++) {
            GameComic gc = gcs.get(i);

            if (gc.getGamecomic_type() == 1)
                continue;

            if ((gc.getPanel1_id() != null && panelService.getPanelFromPanelId(gc.getPanel1_id()).getAuthor().equals(username)) ||
                    (gc.getPanel2_id() != null && panelService.getPanelFromPanelId(gc.getPanel2_id()).getAuthor().equals(username)) ||
                    (gc.getPanel3_id() != null && panelService.getPanelFromPanelId(gc.getPanel3_id()).getAuthor().equals(username)) ||
                    (gc.getPanel4_id() != null && panelService.getPanelFromPanelId(gc.getPanel4_id()).getAuthor().equals(username)))
                continue;

            if (gc.getPanel1_id() == null) {
                availableIds.add(gc.getGame_comic_id());
                continue;
            }
            else if (panelService.getPanelFromPanelId(gc.getPanel1_id()).getAuthor() == null) {
                availableIds.add(gc.getGame_comic_id());
                continue;
            }

            if (gc.getPanel2_id() == null) {
                availableIds.add(gc.getGame_comic_id());
                continue;
            }
            else if (panelService.getPanelFromPanelId(gc.getPanel2_id()).getAuthor() == null) {
                availableIds.add(gc.getGame_comic_id());
                continue;
            }

            if (gc.getPanel3_id() == null) {
                availableIds.add(gc.getGame_comic_id());
                continue;
            }
            else if (panelService.getPanelFromPanelId(gc.getPanel3_id()).getAuthor() == null) {
                availableIds.add(gc.getGame_comic_id());
                continue;
            }
            if (gc.getPanel4_id() == null) {
                availableIds.add(gc.getGame_comic_id());
                continue;
            }
            else if (panelService.getPanelFromPanelId(gc.getPanel4_id()).getAuthor() == null) {
                availableIds.add(gc.getGame_comic_id());
                continue;
            }


        }
        if (availableIds.size() > 0) {
            Integer num = rand.nextInt(availableIds.size());
            Integer id = availableIds.get(num);
            gameId = id;
            if (gameComicService.getGameComicByGameComicId(id).getPanel1_id() == null) {
                Panel panel = new Panel();
                panel.setAuthor(username);
                panelService.addPanel(panel);
                gameComicService.getGameComicByGameComicId(id).setPanel1_id(panel.getPanel_id());
                gameComicService.addGameComic(gameComicService.getGameComicByGameComicId(id));
                panelNo = 1;
            }
            else if (panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel1_id()).getAuthor() == null) {
                panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel1_id()).setAuthor(username);
                panelService.addPanel(panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel1_id()));
                panelNo = 1;
            }
            else if (gameComicService.getGameComicByGameComicId(id).getPanel2_id() == null) {
                Panel panel = new Panel();
                panel.setAuthor(username);
                panelService.addPanel(panel);
                gameComicService.getGameComicByGameComicId(id).setPanel2_id(panel.getPanel_id());
                gameComicService.addGameComic(gameComicService.getGameComicByGameComicId(id));
                panelNo = 2;
            }
            else if (panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel2_id()).getAuthor() == null) {
                panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel2_id()).setAuthor(username);
                panelService.addPanel(panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel2_id()));
                panelNo = 2;
            }
            else if (gameComicService.getGameComicByGameComicId(id).getPanel3_id() == null) {
                Panel panel = new Panel();
                panel.setAuthor(username);
                panelService.addPanel(panel);
                gameComicService.getGameComicByGameComicId(id).setPanel3_id(panel.getPanel_id());
                gameComicService.addGameComic(gameComicService.getGameComicByGameComicId(id));
                panelNo = 3;
            }
            else if (panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel3_id()).getAuthor() == null) {
                panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel3_id()).setAuthor(username);
                panelService.addPanel(panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel3_id()));
                panelNo = 3;
            }
            else if (gameComicService.getGameComicByGameComicId(id).getPanel4_id() == null) {
                Panel panel = new Panel();
                panel.setAuthor(username);
                panelService.addPanel(panel);
                gameComicService.getGameComicByGameComicId(id).setPanel4_id(panel.getPanel_id());
                gameComicService.addGameComic(gameComicService.getGameComicByGameComicId(id));
                panelNo = 4;
            }
            else {
                panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel4_id()).setAuthor(username);
                panelService.addPanel(panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel4_id()));
                panelNo = 4;
            }
        }
        else {
            List<Keyword> keywords = keywordService.getAllKeywords();
            Integer num = rand.nextInt(keywords.size());
            String keyword = keywords.get(num).getKeyword();
            System.out.println(keyword);
            Panel panel1 = new Panel();
            panel1.setAuthor(username);
            panelService.addPanel(panel1);
            Comic comic = new Comic();
            comic.setComic_type(1);
            comic.setStatus(0);
            comicService.addComic(comic);
            GameComic gc = new GameComic();
            gc.setGame_comic_id(comic.getComic_id());
            gc.setGamecomic_type(0);
            gc.setPanel1_id(panel1.getPanel_id());
            gc.setKeyword(keyword);
            gameComicService.addGameComic(gc);
            gameId = comic.getComic_id();
            panelNo = 1;
        }
        /*
            Definition of an avaliable game:
                A game which is not published and no other user is working on this game at the moment
                (Check the panels in the game, if there is a panel which author is not null and canvas_path is null,
                it means someone is working on the game)

            Find a random avaliable public game and assign the user to that game
            record the start time
            return the gameId and panelNo 

            If there are no game avaliable in database create a new one
            record start time

            DONE
        */

        System.out.println("USER PLAYING: " + username);
        System.out.println("Random GameID : " + gameId);
        System.out.println("PANEL NUMBER : " + panelNo);

        JSONObject result = new JSONObject();
        result.put("gameId",gameId);
        result.put("panelNo",panelNo);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value = "/customCreate", method = RequestMethod.POST)
    @ResponseBody
    public String customCreate(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        String keyword = request.getParameter("keyword");
        Integer gameId = 0;
        Integer panelNo = 0;
        List<Keyword> keywords = keywordService.getAllKeywords();
        /*
            Create a new public game with the keyword

            Assign the user to the first panel of this game
            record start time

            return the gameId and panelNo 

            DONE
        */

        Panel panel = new Panel();
        panel.setAuthor(username);
        panelService.addPanel(panel);
        Comic comic = new Comic();
        comic.setComic_type(1);
        comic.setStatus(0);
        comicService.addComic(comic);
        GameComic gc = new GameComic();
        gc.setGame_comic_id(comic.getComic_id());
        gc.setGamecomic_type(0);
        gc.setPanel1_id(panel.getPanel_id());
        gc.setKeyword(keyword);
        gameComicService.addGameComic(gc);
        boolean flag = false;
        for (int i = 0; i < keywords.size(); i++){
            if (keywords.get(i).getKeyword().equals(keyword))
                flag = true;
        }
        System.out.println(keyword);
        System.out.println("000000000000000000000");
        System.out.println(flag);
        if (flag == false){
            Keyword k = new Keyword();
            k.setKeyword(keyword);
            keywordService.addKeyword(k);
        }

        gameId = comic.getComic_id();
        panelNo = 1;


        System.out.println(username);
        System.out.println(keyword);
        System.out.println(gameId);
        System.out.println(panelNo);

        JSONObject result = new JSONObject();
        result.put("gameId",gameId);
        result.put("panelNo",panelNo);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value = "/customExist", method = RequestMethod.POST)
    @ResponseBody
    public String customExist(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        String keyword = request.getParameter("keyword");
        Integer gameId = 0;
        Integer panelNo = 0;
        Boolean exist = false;
        Random rand = new Random();

        List<GameComic> gcs = gameComicService.getAllGameComics();
        List<Integer> sameKeywordIds = new ArrayList<>();
        for(int i = 0; i < gcs.size(); i++) {
            GameComic gc = gcs.get(i);

            if (gc.getGamecomic_type() == 1)
                continue;

            if ((gc.getPanel1_id() != null && panelService.getPanelFromPanelId(gc.getPanel1_id()).getAuthor().equals(username)) ||
                    (gc.getPanel2_id() != null && panelService.getPanelFromPanelId(gc.getPanel2_id()).getAuthor().equals(username)) ||
                    (gc.getPanel3_id() != null && panelService.getPanelFromPanelId(gc.getPanel3_id()).getAuthor().equals(username)) ||
                    (gc.getPanel4_id() != null && panelService.getPanelFromPanelId(gc.getPanel4_id()).getAuthor().equals(username)))
                continue;

            if (gc.getPanel1_id() == null){
                if (gc.getKeyword().equals(keyword)) {
                    sameKeywordIds.add(gc.getGame_comic_id());
                    continue;
                }
            }
            else if (gc.getKeyword().equals(keyword) &&
                    panelService.getPanelFromPanelId(gc.getPanel1_id()).getAuthor() == null){
                sameKeywordIds.add(gc.getGame_comic_id());
                continue;
            }

            if (gc.getPanel2_id() == null){
                if (gc.getKeyword().equals(keyword)) {
                    sameKeywordIds.add(gc.getGame_comic_id());
                    continue;
                }
            }
            else if (gc.getKeyword().equals(keyword) &&
                    panelService.getPanelFromPanelId(gc.getPanel2_id()).getAuthor() == null){
                sameKeywordIds.add(gc.getGame_comic_id());
                continue;
            }

            if (gc.getPanel3_id() == null){
                if (gc.getKeyword().equals(keyword)) {
                    sameKeywordIds.add(gc.getGame_comic_id());
                    continue;
                }
            }
            else if (gc.getKeyword().equals(keyword) &&
                    panelService.getPanelFromPanelId(gc.getPanel3_id()).getAuthor() == null){
                sameKeywordIds.add(gc.getGame_comic_id());
                continue;
            }

            if (gc.getPanel4_id() == null){
                if (gc.getKeyword().equals(keyword)) {
                    sameKeywordIds.add(gc.getGame_comic_id());
                    continue;
                }
            }
            else if (gc.getKeyword().equals(keyword) &&
                    panelService.getPanelFromPanelId(gc.getPanel4_id()).getAuthor() == null){
                sameKeywordIds.add(gc.getGame_comic_id());
                continue;
            }
        }
        /*
            Search all the public games in the database by the given keyword

            If there is an avaliable game{
                assign the user into the game
                record start time
                set exist to true
                return the exist,gameId and panelNo 
            }else{
                set exist to false
                return the exist,gameId and panelNo
                (we will check the exist status in the page first, so the value of gameId and panelNo does not matter)
            }

            DONE
        */
        if (sameKeywordIds.size() > 0) {
            Integer num = rand.nextInt(sameKeywordIds.size());
            Integer id = sameKeywordIds.get(num);
            gameId = id;
            if (gameComicService.getGameComicByGameComicId(id).getPanel1_id() == null) {
                Panel panel = new Panel();
                panel.setAuthor(username);
                panelService.addPanel(panel);
                gameComicService.getGameComicByGameComicId(id).setPanel1_id(panel.getPanel_id());
                gameComicService.addGameComic(gameComicService.getGameComicByGameComicId(id));
                panelNo = 1;
            }
            else if (panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel1_id()).getAuthor() == null) {
                panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel1_id()).setAuthor(username);
                panelService.addPanel(panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel1_id()));
                panelNo = 1;
            }
            else if (gameComicService.getGameComicByGameComicId(id).getPanel2_id() == null) {
                Panel panel = new Panel();
                panel.setAuthor(username);
                panelService.addPanel(panel);
                gameComicService.getGameComicByGameComicId(id).setPanel2_id(panel.getPanel_id());
                gameComicService.addGameComic(gameComicService.getGameComicByGameComicId(id));
                panelNo = 2;
            }
            else if (panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel2_id()).getAuthor() == null) {
                panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel2_id()).setAuthor(username);
                panelService.addPanel(panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel2_id()));
                panelNo = 2;
            }
            else if (gameComicService.getGameComicByGameComicId(id).getPanel3_id() == null) {
                Panel panel = new Panel();
                panel.setAuthor(username);
                panelService.addPanel(panel);
                gameComicService.getGameComicByGameComicId(id).setPanel3_id(panel.getPanel_id());
                gameComicService.addGameComic(gameComicService.getGameComicByGameComicId(id));
                panelNo = 3;
            }
            else if (panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel3_id()).getAuthor() == null) {
                panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel3_id()).setAuthor(username);
                panelService.addPanel(panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel3_id()));
                panelNo = 3;
            }
            else if (gameComicService.getGameComicByGameComicId(id).getPanel4_id() == null) {
                Panel panel = new Panel();
                panel.setAuthor(username);
                panelService.addPanel(panel);
                gameComicService.getGameComicByGameComicId(id).setPanel4_id(panel.getPanel_id());
                gameComicService.addGameComic(gameComicService.getGameComicByGameComicId(id));
                panelNo = 4;
            }
            else {
                panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel4_id()).setAuthor(username);
                panelService.addPanel(panelService.getPanelFromPanelId(gameComicService.getGameComicByGameComicId(id).getPanel4_id()));
                panelNo = 4;
            }
            exist = true;
        }

        System.out.println(username);
        System.out.println(keyword);
        System.out.println(exist);
        System.out.println(gameId);
        System.out.println(panelNo);

        JSONObject result = new JSONObject();
        result.put("exist",exist);
        result.put("gameId",gameId);
        result.put("panelNo",panelNo);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value = "/privateGame", method = RequestMethod.POST)
    @ResponseBody
    public String privateGame(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        String keyword = request.getParameter("keyword");
        String user2 = request.getParameter("user2");
        String user3 = request.getParameter("user3");
        String user4 = request.getParameter("user4");
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
        Integer gameId = 0;
        Integer panelNo = 1;

        /*
            Create a new private game with the keyword

            Check if user2, user3 and user4 exist (There might be null or undefined user)
            Assign username to first panel, user2 to second panel, user3 to third panel, user4 to fourth panel
            Notify all the users in the game

            return the gameId and panelNo 

            DONE
        */

        Comic comic = new Comic();
        comic.setComic_type(1);
        comic.setStatus(0);
        comicService.addComic(comic);
        GameComic gc = new GameComic();
        gc.setGame_comic_id(comic.getComic_id());
        gc.setGamecomic_type(1);

        gameId = comic.getComic_id();
        if (this.accountService.usernameExist(user2) == true){
            Date now = new Date();
            int notification_type = 6;
            Notification note = new Notification();
            note.setNotification_type(notification_type);
            note.setNotification(username + " has invited you to draw panel 2 in the comicGame with id " + gameId);
            note.setUsername(user2);
            note.setNotifcation_time(now);
            note.setLink(Integer.toString(gameId) + "  2");
            this.notificationService.addNotification(note);
        }
        if (this.accountService.usernameExist(user3) == true){
            Date now = new Date();
            int notification_type = 6;
            Notification note = new Notification();
            note.setNotification_type(notification_type);
            note.setNotification(username + " has invited you to draw panel 3 in the comicGame with id " + gameId);
            note.setUsername(user3);
            note.setNotifcation_time(now);
            note.setLink(Integer.toString(gameId) + "  3");
            this.notificationService.addNotification(note);

        }
        if(this.accountService.usernameExist(user4) == true){
            Date now = new Date();
            int notification_type = 6;
            Notification note = new Notification();
            note.setNotification_type(notification_type);
            note.setNotification(username + " has invited you to draw panel 4 in the comicGame with id " + gameId);
            note.setUsername(user4);
            note.setNotifcation_time(now);
            note.setLink(Integer.toString(gameId)+ "  4");
            this.notificationService.addNotification(note);
        }

        Panel panel1 = new Panel();
        panel1.setAuthor(username);
        panelService.addPanel(panel1);
        gc.setPanel1_id(panel1.getPanel_id());
        if (user2 != null) {
            if (this.accountService.usernameExist(user2) == true) {
                Panel panel2 = new Panel();
                panel2.setAuthor(user2);
                panelService.addPanel(panel2);
                gc.setPanel2_id(panel2.getPanel_id());
            }
        }
        if (!user3.isEmpty()) {
            if (this.accountService.usernameExist(user3) == true) {
                Panel panel3 = new Panel();
                panel3.setAuthor(user3);
                panelService.addPanel(panel3);
                gc.setPanel3_id(panel3.getPanel_id());
            }
        }
        if (!user4.isEmpty()) {
            if (this.accountService.usernameExist(user4) == true) {
                Panel panel4 = new Panel();
                panel4.setAuthor(user4);
                panelService.addPanel(panel4);
                gc.setPanel4_id(panel4.getPanel_id());
            }
        }
        gc.setKeyword(keyword);
        gameComicService.addGameComic(gc);

        List<Keyword> keywords = keywordService.getAllKeywords();
        boolean flag = false;
        for (int i = 0; i < keywords.size(); i++){
            if (keywords.get(i).getKeyword().equals(keyword))
                flag = true;
        }
        if (flag == false){
            Keyword k = new Keyword();
            k.setKeyword(keyword);
            keywordService.addKeyword(k);
        }


        System.out.println(username);
        System.out.println(keyword);
        System.out.println(gameId);
        System.out.println(panelNo);

        JSONObject result = new JSONObject();
        result.put("gameId",gameId);
        result.put("panelNo",panelNo);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    @ResponseBody
    public String getInfo(HttpServletRequest request) throws IOException {
        String gameID = request.getParameter("gameId");
        String cur = request.getParameter("current");
        System.out.println("GAME ID GETINFO: " + gameID);
        System.out.println("CUR GET INFO " + cur);
        Integer gameid = Integer.parseInt(gameID);
        Integer currentPanel = Integer.parseInt(cur);
        GameComic gc = gameComicService.getGameComicByGameComicId(gameid);
        List<String> path = new ArrayList<>();
        List<String> word = new ArrayList<>();
        List<String> num = new ArrayList<>();
        String keyword = gc.getKeyword();
        if(currentPanel==1){
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
        result.put("num", num);
        result.put("path", path);
        result.put("word", word);
        result.put("keyword", keyword);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value = "/randomKeyword", method = RequestMethod.POST)
    @ResponseBody
    public String randomKeyword(HttpServletRequest request) {
        List<Keyword> keywords = keywordService.getAllKeywords();
        Random rand = new Random();
        Integer num = rand.nextInt(keywords.size());
        String keyword = keywords.get(num).getKeyword();
        /*

            SELECT A RANDOM KEYWORD FROM KEYWORD TABLE

                DONE
        */

        return keyword;
    }
}
