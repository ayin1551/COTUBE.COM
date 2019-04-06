package cotube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import org.json.*;

@Controller
@RequestMapping(value="/viewComics.html")
public class ajaxViewComicsController{
    @RequestMapping(value="/checkAuthor",method = RequestMethod.POST)
    @ResponseBody
    public Boolean checkAuthor(HttpServletRequest request){
        String username = request.getParameter("username");
        String comicName = request.getParameter("comic_name");
        System.out.println(username);
        System.out.println(comicName);


        return true;
    }
}
