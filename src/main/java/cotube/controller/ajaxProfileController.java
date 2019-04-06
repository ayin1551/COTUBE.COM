package cotube.controller;

import cotube.domain.FollowUser;
import cotube.services.FollowUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import org.json.*;

import java.util.Date;

@Controller
@RequestMapping(value="/profile.html")
public class ajaxProfileController{

    private FollowUserService followUserService;
    @Autowired
    public void setFollowUserService(FollowUserService followUserService) {
        this.followUserService = followUserService;
    }



    @RequestMapping(value="/follow",method = RequestMethod.POST)
    @ResponseBody
    public Boolean follow(HttpServletRequest request){
        String username = request.getParameter("username"); //follower
        String following = request.getParameter("following"); //following
        FollowUser followUser = new FollowUser();
        followUser.setFollower_username(username);
        followUser.setFollowing_username(following);
        followUser.follow_time = new Date();
        followUserService.addFollowUser(followUser);
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
