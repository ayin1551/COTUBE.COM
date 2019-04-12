package cotube.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/viewComics.html")
public class ajaxViewComicsController{
    @RequestMapping(value="/checkLike",method = RequestMethod.POST)
    @ResponseBody
    public Boolean checkLike(HttpServletRequest request){
        String username = request.getParameter("username");
        String comicid = request.getParameter("comic_id");
        System.out.println(username);
        System.out.println(comicid);


        return true;
    }

    @RequestMapping(value="/toggleLike",method = RequestMethod.POST)
    @ResponseBody
    public Boolean toggleLike(HttpServletRequest request){
        String username = request.getParameter("username");
        String comicid = request.getParameter("comic_id");
        Boolean like = request.getParameter("like").equals("true")?true:false;
        System.out.println(username);
        System.out.println(comicid);
        System.out.println(like);

        return true;
    }

    @RequestMapping(value="/postComment",method = RequestMethod.POST)
    @ResponseBody
    public Boolean postComment(HttpServletRequest request){
        String username = request.getParameter("username");
        String comicid = request.getParameter("comic_id");
        String comment = request.getParameter("comment");
        System.out.println(username);
        System.out.println(comicid);
        System.out.println(comment);

        return true;
    }
}
