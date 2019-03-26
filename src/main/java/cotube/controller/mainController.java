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
}
