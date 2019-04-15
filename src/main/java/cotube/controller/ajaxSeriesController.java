package cotube.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/series.html")
public class ajaxSeriesController{


    @RequestMapping(value="/follow",method = RequestMethod.POST)
    @ResponseBody
    public Boolean follow(HttpServletRequest request){
        String username = request.getParameter("username");
        String following = request.getParameter("following");
        System.out.println(username);
        System.out.println(following);


        return true;
    }

    @RequestMapping(value="/unfollow",method = RequestMethod.POST)
    @ResponseBody
    public Boolean unfollow(HttpServletRequest request){
        String username = request.getParameter("username");
        String unfollowing = request.getParameter("unfollowing");
        System.out.println(username);
        System.out.println(unfollowing);


        return true;
    }


    // Check if the user has already followed this series
    @RequestMapping(value="/check",method = RequestMethod.POST)
    @ResponseBody
    public Boolean check(HttpServletRequest request){
        String username = request.getParameter("username");
        String following = request.getParameter("following");
        System.out.println(username);
        System.out.println(following);


        return true;
    }
}
