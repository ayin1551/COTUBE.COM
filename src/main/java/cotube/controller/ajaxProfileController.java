package cotube.controller;

import cotube.domain.FollowUser;
import cotube.domain.Account;
import cotube.domain.Folder;
import cotube.domain.FollowSeries;
import cotube.domain.Series;

import cotube.services.FollowUserService;
import cotube.services.AccountService;
import cotube.services.FolderService;
import cotube.services.FollowSeriesService;
import cotube.services.SeriesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/profile.html")
public class ajaxProfileController{

    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private FollowUserService followUserService;
    @Autowired
    public void setFollowUserService(FollowUserService followUserService) {
        this.followUserService = followUserService;
    }

    private FolderService folderService;
    @Autowired
    public void setFolderService(FolderService folderService) {
        this.folderService = folderService;
    }

    private FollowSeriesService followSeriesService;
    @Autowired
    public void setFollowSeriesService(FollowSeriesService followSeriesService) {
        this.followSeriesService = followSeriesService;
    }

    private SeriesService seriesService;
    @Autowired
    public void setSeriesService(SeriesService seriesService) {
        this.seriesService = seriesService;
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
        // System.out.println(username);
        // System.out.println(following);
        // System.out.println("!!!!!WANTED!!!!!follower:" + username + "\tfollowing:" + following);
        List<FollowUser>all = this.followUserService.getAllFollowUsers();
        for (FollowUser f: all){
            // System.out.println("follower: " + f.getFollower_username() + "\tfollowing:" + f.getFollowing_username());
            if(f.getFollower_username().equals(username)&&f.getFollowing_username().equals(following)){
                // System.out.println("Yes!\n");
                return true;
            }
        }
        // System.out.println("No!\n");
        return false;
    }

    @RequestMapping(value="/getFollowingCount",method = RequestMethod.POST)
    @ResponseBody
    public Integer getFollowingCount(HttpServletRequest request){
        String username = request.getParameter("username");
        System.out.println(username);
        int count = 0;
        List<FollowUser> follows = followUserService.getAllFollowUsers();
        for(FollowUser list: follows){
            if(list.getFollower_username().equals(username)){
                count++;
            }
        }
        return count;
    }

    @RequestMapping(value="/getFollowerCount",method = RequestMethod.POST)
    @ResponseBody
    public Integer getFollowerCount(HttpServletRequest request){
        String username = request.getParameter("username");
        System.out.println(username);
        List<Account> accounts = accountService.getAllAccounts();
        for(Account acc: accounts){
            if(acc.getUsername().equals(username)){
                return followUserService.getFollowerCount(acc.getUsername());
            }
        }
        return -1;
    }

    @RequestMapping(value="/getFollowerList",method = RequestMethod.POST)
    @ResponseBody
    public String getFollowerList(HttpServletRequest request){
        String username = request.getParameter("username");
        // System.out.println(username);
        List<FollowUser> follows = followUserService.getAllFollowUsers();

        List<String> name = new ArrayList<String>();
        List<String> pic = new ArrayList<String>();
        for(FollowUser list: follows){
            if (list.getFollowing_username().equals(username)){
                name.add(list.getFollower_username());
                List<Account> accounts = accountService.getAllAccounts();
                for(Account acc: accounts){
                    if(acc.getUsername().equals(list.getFollower_username())){
                        pic.add(acc.getProfile_pic_path());
                        break;
                    }
                }
                
            }
        }
        for(int i=0;i<name.size();i++){
            System.out.println(i+1 + ": " + name.get(i) + "\t" + pic.get(i));
        }
        JSONObject result = new JSONObject();
        result.put("account", name);
        result.put("picpath", pic);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/getFollowingList",method = RequestMethod.POST)
    @ResponseBody
    public String getFollowingList(HttpServletRequest request){
        String username = request.getParameter("username");
        // System.out.println(username);
        List<FollowUser> follows = followUserService.getAllFollowUsers();

        List<String> name = new ArrayList<String>();
        List<String> pic = new ArrayList<String>();
        for(FollowUser list: follows){
            if (list.getFollower_username().equals(username)){
                name.add(list.getFollowing_username());
                List<Account> accounts = accountService.getAllAccounts();
                for(Account acc: accounts){
                    if(acc.getUsername().equals(list.getFollowing_username())){
                        pic.add(acc.getProfile_pic_path());
                        break;
                    }
                }
                
            }
        }
        for(int i=0;i<name.size();i++){
            System.out.println(i+1 + ": " + name.get(i) + "\t" + pic.get(i));
        }
        JSONObject result = new JSONObject();
        result.put("account", name);
        result.put("picpath", pic);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/getPublicFavorites",method = RequestMethod.POST)
    @ResponseBody
    public String getPublicFavorites(HttpServletRequest request){
        String username = request.getParameter("username");
        List<Folder> folders = folderService.getAllFolders();

        List<String> folderName = new ArrayList<String>();
        List<Integer> folderId = new ArrayList<Integer>();

        for(Folder folder: folders){
            if (folder.getUsername().equals(username) && folder.getVisibility()==1 && folder.getFolder_type()==0){
                folderName.add(folder.getFolder_name());
                folderId.add(folder.getFolder_id());
            }
        }
        for(int i=0;i<folderName.size();i++){
            System.out.println(i+1 + ": " + folderName.get(i));
        }
        JSONObject result = new JSONObject();
        result.put("folderName", folderName);
        result.put("folderId", folderId);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/getMyFavorites",method = RequestMethod.POST)
    @ResponseBody
    public String getMyFavorites(HttpServletRequest request){
        String username = request.getParameter("username");
        List<Folder> folders = folderService.getAllFolders();

        List<String> folderName = new ArrayList<String>();
        List<Integer> folderId = new ArrayList<Integer>();

        for(Folder folder: folders){
            if (folder.getUsername().equals(username) && folder.getFolder_type()==0){
                folderName.add(folder.getFolder_name());
                folderId.add(folder.getFolder_id());
            }
        }
        for(int i=0;i<folderName.size();i++){
            System.out.println(i+1 + ": " + folderName.get(i));
        }
        JSONObject result = new JSONObject();
        result.put("folderName", folderName);
        result.put("folderId", folderId);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/getSeries",method = RequestMethod.POST)
    @ResponseBody
    public String getSeries(HttpServletRequest request){
        String username = request.getParameter("username");
        List<FollowSeries> followSeries = followSeriesService.getAllFollowSeries();



        List<Series> series = seriesService.getAllSeries();

        List<Integer> seriesId = new ArrayList<Integer>();

        for(FollowSeries f: followSeries){
            if(f.getFollower_username().equals(username)){
                for(Series s: series){
                    if(s.getSeries_id() == f.getSeries_id()){
                        seriesId.add(s.getSeries_id());
                        break;
                    }
                }
            }
        }


        for(int i=0;i<seriesId.size();i++){
            System.out.println(i+1 + ": " + seriesId.get(i));
        }
        JSONObject result = new JSONObject();
        result.put("folderId", seriesId);
        System.out.println(result.toString());
        return result.toString();
    }
}
