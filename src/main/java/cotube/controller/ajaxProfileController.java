package cotube.controller;

import cotube.domain.FollowUser;
import cotube.services.FollowUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

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
        System.out.println("Follow");
        followUserService.addFollowUser(followUser);
        return true;
    }

    @RequestMapping(value="/unfollow",method = RequestMethod.POST)
    @ResponseBody
    public Boolean unfollow(HttpServletRequest request){
        String username = request.getParameter("username");
        String unfollowing = request.getParameter("unfollowing");
        List<FollowUser> followList = this.followUserService.getAllFollowUsers();
        for(FollowUser f: followList){
            if (f.getFollower_username().equals(username)){
                if(f.getFollowing_username().equals(unfollowing)){
                    this.followUserService.deleteFollowerUser(f);
                    return true;
                }
            }
        }
        return false;
    }

    @RequestMapping(value="/check",method = RequestMethod.POST)
    @ResponseBody
    public Boolean check(HttpServletRequest request){
        String username = request.getParameter("username");
        String following = request.getParameter("following");
        System.out.println(username);
        System.out.println(following);
        List<FollowUser>all = this.followUserService.getAllFollowUsers();
        for (FollowUser f: all){
            if(f.getFollower_username().equals(following)){
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value="/getFollowingCount",method = RequestMethod.POST)
    @ResponseBody
    public Integer getFollowingCount(HttpServletRequest request){
        String username = request.getParameter("username");
        System.out.println(username);
        
        return 1;
    }

    @RequestMapping(value="/getFollowerCount",method = RequestMethod.POST)
    @ResponseBody
    public Integer getFollowerCount(HttpServletRequest request){
        String username = request.getParameter("username");
        System.out.println(username);
        
        return 2;
    }

}
