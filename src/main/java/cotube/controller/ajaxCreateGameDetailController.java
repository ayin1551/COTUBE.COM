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

        Panel panel = panelService.getPanelFromPanelId(panelNo);
        String fileName = "gc_ " + comicId + "_panelID_" + panel.getPanel_id() + ".png";
        File outputfile = new File("tmp/" + fileName); //file path and file name need to change
        panel.setCanvas_path("comicID_" + comicId + ".png");
        panelService.addPanel(panel);


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
        Integer gameId = Integer.parseInt(request.getParameter("gameId"));
        Integer panelNo = Integer.parseInt(request.getParameter("panelNo"));

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
            }
        */

        return true;
    }

    @RequestMapping(value = "/cancelPublic", method = RequestMethod.POST)
    @ResponseBody
    public Boolean cancelPublicGame(HttpServletRequest request) {
        String username = request.getParameter("username");
        Integer gameId = Integer.parseInt(request.getParameter("gameId"));
        Integer panelNo = Integer.parseInt(request.getParameter("panelNo"));

        /*
            Check if the user is still in the game
            if not{
                return true directly
            }
            else{
                delete the panel he created
                remove the user from the game in the database
                if the game is empty delete the game
                return true   
            }

        */

        return true;
    }

    @RequestMapping(value = "/cancelPrivate", method = RequestMethod.POST)
    @ResponseBody
    public Boolean cancelPrivateGame(HttpServletRequest request) {
        String username = request.getParameter("username");
        Integer gameId = Integer.parseInt(request.getParameter("gameId"));
        Integer panelNo = Integer.parseInt(request.getParameter("panelNo"));

        /*
            Check if the user is still in the game
            if not{
                return true directly
            }
            else{
                remove canvas_path of the panel in the database
                DO NOT!!!!remove the user from the game in the database
                return true   
            }
            
        */

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

}
