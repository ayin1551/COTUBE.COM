package cotube.controller;

import cotube.domain.*;

import cotube.services.*;

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
import java.util.Collections;

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

    private FavoriteService favoriteService;
    @Autowired
    public void setFavoriteService(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    private ComicService comicService;
    @Autowired
    public void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }

    private CommentsService commentsService;
    @Autowired
    public void setCommentsService(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    private RegularComicService regularComicService;
    @Autowired
    public void setRegularComicService(RegularComicService regularComicService) {
        this.regularComicService = regularComicService;
    }

    private PanelService panelService;
    @Autowired
    public void setPanelService(PanelService panelService) {
        this.panelService = panelService;
    }

    private ViewsService viewsService;
    @Autowired
    public void setViewsService(ViewsService viewsService) {
        this.viewsService = viewsService;
    }

    private LikesService likesService;
    @Autowired
    public void setLikesService(LikesService likesService) {
        this.likesService = likesService;
    }

    private TagService tagService;
    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
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

    @RequestMapping(value="/getProfilePic",method = RequestMethod.POST)
    @ResponseBody
    public String getProfilePic(HttpServletRequest request){
        String username = request.getParameter("username");
        return accountService.getAccountByUsername(username).getProfile_pic_path();
    }


    @RequestMapping(value="/getViews",method = RequestMethod.POST)
    @ResponseBody
    public Integer getViews(HttpServletRequest request){
        String username = request.getParameter("username");
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        List<Panel> panel = panelService.getAllPanels();
        List<Views> views = viewsService.getAllViews();

        int count = 0;

        List<Integer> comicId = new ArrayList<Integer>();

        for(Panel p: panel){
            if(p.getAuthor().equals(username)){
                for(RegularComic rc: regularComics){
                    if(rc.getPanel_id() == p.getPanel_id()){
                        comicId.add(rc.getRegular_comic_id());
                    }
                }
            }
        }

        for(Integer i: comicId){
            for(Views v: views){
                if(v.getComic_id() == i){
                    count +=1;
                }
            }
        }
        return count;
    }

    @RequestMapping(value="/getLikes",method = RequestMethod.POST)
    @ResponseBody
    public Integer getLikes(HttpServletRequest request){
        String username = request.getParameter("username");
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        List<Panel> panel = panelService.getAllPanels();
        List<Likes> likes = likesService.getAllLikes();

        int count = 0;

        List<Integer> comicId = new ArrayList<Integer>();

        for(Panel p: panel){
            if(p.getAuthor().equals(username)){
                for(RegularComic rc: regularComics){
                    if(rc.getPanel_id() == p.getPanel_id()){
                        comicId.add(rc.getRegular_comic_id());
                    }
                }
            }
        }

        for(Integer i: comicId){
            for(Likes l: likes){
                if(l.getComic_id() == i){
                    count +=1;
                }
            }
        }
        return count;
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

        List<String> seriesName = new ArrayList<String>();
        List<Integer> seriesId = new ArrayList<Integer>();
        List<String> seriesThumbnail = new ArrayList<String>();

        for(FollowSeries f: followSeries){
            if(f.getFollower_username().equals(username)){
                for(Series s: series){
                    if(s.getSeries_id() == f.getSeries_id()){
                        seriesName.add(s.getSeries_name());
                        seriesId.add(s.getSeries_id());
                        seriesThumbnail.add(s.getSeries_thumbnail_path());
                        break;
                    }
                }
            }
        }


        for(int i=0;i<seriesId.size();i++){
            System.out.println(i+1 + ": " + seriesId.get(i));
        }
        JSONObject result = new JSONObject();
        result.put("seriesName", seriesName);
        result.put("seriesId", seriesId);
        result.put("seriesThumbnail", seriesThumbnail);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/getMyComics",method = RequestMethod.POST)
    @ResponseBody
    public String getMyComic(HttpServletRequest request){
        String username = request.getParameter("username");
        List<Comic> comics = comicService.getAllComics();
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        List<Panel> panel = panelService.getAllPanels();

        List<String> comicName = new ArrayList<String>();
        List<Integer> comicId = new ArrayList<Integer>();
        List<String> comicThumbnail = new ArrayList<String>();
        List<Boolean> comicSeries = new ArrayList<Boolean>();

        for(Panel p: panel){
            if(p.getAuthor().equals(username)){
                for(RegularComic rc: regularComics){
                    for(Comic c: comics){
                        if(rc.getPanel_id() == p.getPanel_id() && c.getComic_id() == rc.getRegular_comic_id()){
                            comicId.add(rc.getRegular_comic_id());
                        }
                    }
                }
            }
        }

        Collections.sort(comicId);
        Collections.reverse(comicId);

        for(Integer i: comicId){
            for(Comic c: comics){
                if(c.getComic_id() == i){
                    comicName.add(c.getTitle());
                    break;
                }
            }

            for(RegularComic rc: regularComics){
                if(rc.getRegular_comic_id() == i){
                    comicThumbnail.add(rc.getThumbnail_path());
                    comicSeries.add(rc.getSeries_id()==null?false:true);
                    break;
                }
            }
        }

        JSONObject result = new JSONObject();
        result.put("comicName", comicName);
        result.put("comicId", comicId);
        result.put("comicThumbnail", comicThumbnail);
        result.put("comicSeries", comicSeries);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/getOthersComics",method = RequestMethod.POST)
    @ResponseBody
    public String getOtherComic(HttpServletRequest request){
        String username = request.getParameter("username");
        List<Comic> comics = comicService.getAllComics();
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        List<Panel> panel = panelService.getAllPanels();

        List<String> comicName = new ArrayList<String>();
        List<Integer> comicId = new ArrayList<Integer>();
        List<String> comicThumbnail = new ArrayList<String>();
        List<Boolean> comicSeries = new ArrayList<Boolean>();

        for(Panel p: panel){
            if(p.getAuthor().equals(username)){
                for(RegularComic rc: regularComics){
                    for(Comic c: comics){
                        if(rc.getPanel_id() == p.getPanel_id() && c.getComic_id() == rc.getRegular_comic_id() && c.getStatus() == 1){
                            comicId.add(rc.getRegular_comic_id());
                        }
                    }
                }
            }
        }

        Collections.sort(comicId);
        Collections.reverse(comicId);

        for(Integer i: comicId){
            for(Comic c: comics){
                if(c.getComic_id() == i){
                    comicName.add(c.getTitle());
                    break;
                }
            }

            for(RegularComic rc: regularComics){
                if(rc.getRegular_comic_id() == i){
                    comicThumbnail.add(rc.getThumbnail_path());
                    comicSeries.add(rc.getSeries_id()==null?false:true);
                    break;
                }
            }
        }

        JSONObject result = new JSONObject();
        result.put("comicName", comicName);
        result.put("comicId", comicId);
        result.put("comicThumbnail", comicThumbnail);
        result.put("comicSeries", comicSeries);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/deleteComic",method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteComic(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        Comic comic = comicService.getComicByComic_Id(comicId);
        int type = comic.getComic_type();
        if (type == 0) {//regular
            RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(comicId);
            Integer series_id = rc.getSeries_id();

            //delete From Tag
            Integer regular_comic_id = comicId;
            List<Tag> tagList = tagService.getAllTagsInRegularComic(regular_comic_id);
            for (int i = 0; i < tagList.size(); i++)
                tagService.deleteTag(tagList.get(i));

            //delete From Views
            List<Views> viewsList = viewsService.getAllViewsInComic(comicId);
            for (int i = 0; i < viewsList.size(); i++)
                viewsService.deleteView(viewsList.get(i));

            //delete from Likes
            List<Likes> likesList = likesService.getAllLikesInComic(comicId);
            for (int i = 0; i < likesList.size(); i++)
                likesService.deleteLike(likesList.get(i));

            //delete from Comments
            List<Comments> commentsList = commentsService.getAllCommentsInComic(comicId);
            for (int i = 0; i < commentsList.size(); i++)
                commentsService.deleteComment(commentsList.get(i));

            //delete from Favorites
            List<Favorite> favoritesList = favoriteService.getAllFavoritesInComic(comicId);
            for (int i = 0; i < favoritesList.size(); i++)
                favoriteService.deleteFavorite(favoritesList.get(i));

            //delete from RegularComic
            regularComicService.deleteRegularComic(rc);

            //delete From Panel
            panelService.deletePanel(panelService.getPanelFromPanelId(rc.getPanel_id()));

            //delete from Comic
            comicService.deleteComic(comic);

            if (series_id != null) {
                List<RegularComic> rcSeriesList = regularComicService.getAllRegularComicsInSeries(series_id);
                if(rcSeriesList.isEmpty()){

                    //delete from FollowSeries
                    List<FollowSeries> followSeriesList = followSeriesService.getAllFollowSeriesInSeries(series_id);
                    for (int i = 0; i < followSeriesList.size(); i++)
                        followSeriesService.deleteFollowSeries(followSeriesList.get(i));

                    //delete from Series
                    seriesService.deleteSeries(seriesService.getSeriesBySeriesId(series_id));

                }
            }
        }
        return false;
    }
}
