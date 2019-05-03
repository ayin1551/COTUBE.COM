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


 

}
