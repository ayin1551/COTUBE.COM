package cotube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class mainController {
    @RequestMapping(value={"/","home.html"})
    String home(){
        return "index";
    }

    @RequestMapping(value={"profile.html"})
    String profile(){
        return "profile";
    }

    @RequestMapping(value={"forget.html"})
    String forget(){
        return "forget";
    }

    @RequestMapping(value={"login.html"})
    String login(){
        return "login";
    }

    @RequestMapping(value={"index.html"})
    String index(){
        return "index";
    }

    @RequestMapping(value={"setting.html"})
    String setting(){
        return "setting";
    }

    @RequestMapping(value={"viewComicsByTitle.html"})
    String viewComicsByTitle(){
        return "viewComicsByTitle";
    }

    @RequestMapping(value={"message.html"})
    String message(){
        return "message";
    }

    @RequestMapping(value={"viewGameComicsByKeyword.html"})
    String viewGameComicsByKeyword(){
        return "viewGameComicsByKeyword";
    }

    @RequestMapping(value={"searchResult.html"})
    String searchResult(){
        return "searchResult";
    }

    @RequestMapping(value={"createHome.html"})
    String createHome(){
        return "createHome";
    }

    @RequestMapping(value={"favorite.html"})
    String favorite(){
        return "favorite";
    }

    @RequestMapping(value={"viewComics.html"})
    String viewComics(){
        return "viewComics";
    }

    @RequestMapping(value={"viewGameComics.html"})
    String viewGameComics(){
        return "viewGameComics";
    }

    @RequestMapping(value={"createComic.html"})
    String createComic(){
        return "createComic";
    }
}
