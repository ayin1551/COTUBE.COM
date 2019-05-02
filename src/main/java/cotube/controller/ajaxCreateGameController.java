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
@RequestMapping(value = "/createGame.html")
public class ajaxCreateGameController {


    @RequestMapping(value = "/playRandom", method = RequestMethod.POST)
    @ResponseBody
    public String playRandom(HttpServletRequest request) throws IOException {
        String username = request.getParameter("username");
        Integer gameId = 0;
        Integer panelNo = 0;

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
        */


        System.out.println(gameId);
        System.out.println(panelNo);

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

        /*
            Create a new public game with the keyword

            Assign the user to the first panel of this game
            record start time

            return the gameId and panelNo 

        */


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

        */

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
        Integer gameId = 0;
        Integer panelNo = 0;

        /*
            Create a new private game with the keyword

            Check if user2, user3 and user4 exist (There might be null or undefined user)
            Assign username to first panel, user2 to second panel, user3 to third panel, user4 to fourth panel
            Notify all the users in the game

            return the gameId and panelNo 

        */

        System.out.println(gameId);
        System.out.println(panelNo);

        JSONObject result = new JSONObject();
        result.put("gameId",gameId);
        result.put("panelNo",panelNo);
        System.out.println(result.toString());
        return result.toString();
    }



 

}
