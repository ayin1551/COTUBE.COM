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
    private NotificationService notificationService;
    @Autowired
    public void setNotificationService(NotificationService notificationService){
        this.notificationService = notificationService;
    }
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

    private GameComicService gameComicService;
    @Autowired
    public void setGameComicService(GameComicService gameComicService){
        this.gameComicService = gameComicService;
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
                    if(rc.getPanel_id().equals(p.getPanel_id())){
                        comicId.add(rc.getRegular_comic_id());
                    }
                }
            }
        }

        for(Integer i: comicId){
            for(Views v: views){
                if(v.getComic_id().equals(i)){
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
                    if(rc.getPanel_id().equals(p.getPanel_id())){
                        comicId.add(rc.getRegular_comic_id());
                    }
                }
            }
        }

        for(Integer i: comicId){
            for(Likes l: likes){
                if(l.getComic_id().equals(i)){
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
                    if(s.getSeries_id()!=null){
                        if(s.getSeries_id().equals(f.getSeries_id())){
                            seriesName.add(s.getSeries_name());
                            seriesId.add(s.getSeries_id());
                            seriesThumbnail.add(s.getSeries_thumbnail_path());
                            break;
                        }
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
        List<GameComic> gameComics = gameComicService.getAllGameComics();

        List<String> comicName = new ArrayList<String>();
        List<Integer> comicId = new ArrayList<Integer>();
        List<String> comicThumbnail = new ArrayList<String>();
        List<Integer> comicSeriesId = new ArrayList<Integer>();
        List<Boolean> comicSeries = new ArrayList<Boolean>();
        List<Boolean> comicGame = new ArrayList<Boolean>();
        List<Boolean> comicGameAdd = new ArrayList<Boolean>();
        List<Boolean> comicGameEdit = new ArrayList<Boolean>();
        List<Boolean> comicGamePublic = new ArrayList<Boolean>();
        List<Integer> comicGamePanelNo = new ArrayList<Integer>();
        List<Boolean> comicGamePublished = new ArrayList<Boolean>();
        List<Boolean> comicClick = new ArrayList<Boolean>();

        Integer comicType = 0;

        for(Panel p: panel){
            Boolean findFlag = false;
            if(p.getAuthor().equals(username)){
                for(RegularComic rc: regularComics){
                    for(Comic c: comics){
                        if(rc.getPanel_id().equals(p.getPanel_id()) && c.getComic_id().equals(rc.getRegular_comic_id())){
                            comicId.add(rc.getRegular_comic_id());
                            findFlag = true;
                        }
                    }
                }
                
                if(!findFlag){
                    for(GameComic gc: gameComics){
                        for(Comic c: comics){
                            Boolean panelMatch = checkMatch(p, gc);
                            if(panelMatch && gc.getGame_comic_id().equals(c.getComic_id())){
                                if(panelService.getPanelFromPanelId(gc.getPanel1_id()).getCanvas_path()!=null){
                                    comicId.add(gc.getGame_comic_id()); 
                                }
                            findFlag = true;
                            }
                        }
                    }
                }
                if(!findFlag){
                    System.out.println("panel not found!");
                }
            }
        }

        Collections.sort(comicId);
        Collections.reverse(comicId);

        for(Integer i: comicId){
            for(Comic c: comics){
                if(c.getComic_id().equals(i)){
                    comicName.add(c.getTitle());
                    comicClick.add((c.getStatus()==1 || c.getStatus()==3)?true:false);
                    comicType = c.getComic_type();
                    break;
                }
            }
            if(comicType == 0){
                for(RegularComic rc: regularComics){
                    if(rc.getRegular_comic_id().equals(i)){
                        comicThumbnail.add(rc.getThumbnail_path());
                        comicSeries.add(rc.getSeries_id()==null?false:true);
                        comicSeriesId.add(rc.getSeries_id()==null?null:rc.getSeries_id());
                        comicGame.add(false);
                        comicGameAdd.add(false);
                        comicGameEdit.add(false);
                        comicGamePublic.add(false);
                        comicGamePanelNo.add(null);
                        comicGamePublished.add(false);
                        break;
                    }
                }
            }else if(comicType == 1){
                // add gameComic to list
                GameComic gc = gameComicService.getGameComicByGameComicId(i);
                Panel p = panelService.getPanelFromPanelId(gc.getPanel1_id());
                Integer panelNo = 1;
                
                if(!p.getAuthor().equals(username)){
                    if(gc.getPanel2_id()!=null){
                        p = panelService.getPanelFromPanelId(gc.getPanel2_id());
                        panelNo = 2;
                        
                    }
                }

                if(!p.getAuthor().equals(username)){
                    if(gc.getPanel3_id()!=null){
                        p = panelService.getPanelFromPanelId(gc.getPanel3_id());
                        panelNo = 3;
                    }
                }

                if(!p.getAuthor().equals(username)){
                    if(gc.getPanel4_id()!=null){
                        p = panelService.getPanelFromPanelId(gc.getPanel4_id());
                        panelNo = 4;
                    }
                }

                comicThumbnail.add(panelService.getPanelFromPanelId(gc.getPanel1_id()).getCanvas_path());
                comicSeries.add(false);
                comicSeriesId.add(null);
                comicGame.add(true);
                comicGamePanelNo.add(panelNo);
                if(p.getCanvas_path() == null){
                    comicGameEdit.add(true);
                }else{
                    comicGameEdit.add(false);
                }
                if(p.getPanel_id().equals(gc.getPanel1_id()) && this.comicService.getComicByComic_Id(i).getStatus() == 0){
                    comicGamePublished.add(false);
                    if(gc.getGamecomic_type() == 1){
                        comicGameAdd.add(true);
                    }else{
                        comicGameAdd.add(false);
                    }   
                    Integer nullCounter = 0;
                    Boolean unfinished = false;
                    if(gc.getPanel1_id()==null){
                        nullCounter++;
                    }else{
                        unfinished = this.panelService.getPanelFromPanelId(gc.getPanel1_id()).getCanvas_path()==null?true:false;
                    }
                    if(gc.getPanel2_id()==null){
                        nullCounter++;
                    }else{
                        if(!unfinished){
                            unfinished = this.panelService.getPanelFromPanelId(gc.getPanel1_id()).getCanvas_path()==null?true:false;
                        }
                    }
                    if(gc.getPanel3_id()==null){
                        nullCounter++;
                    }else{
                        if(!unfinished){
                            unfinished = this.panelService.getPanelFromPanelId(gc.getPanel1_id()).getCanvas_path()==null?true:false;
                        }
                    }
                    if(gc.getPanel4_id()==null){
                        nullCounter++;
                    }else{
                        if(!unfinished){
                            unfinished = this.panelService.getPanelFromPanelId(gc.getPanel1_id()).getCanvas_path()==null?true:false;
                        }
                    }
                    if(nullCounter>=1 && gc.getGamecomic_type() == 1 && !unfinished){
                        comicGamePublic.add(true);
                    }else{
                        comicGamePublic.add(false);
                    }
                }else{
                    if(this.comicService.getComicByComic_Id(i).getStatus() == 0){
                        comicGamePublished.add(false);
                    }else{
                        comicGamePublished.add(true);
                    }
                    comicGameAdd.add(false);
                    comicGamePublic.add(false);
                }
            }
                
        }
        
        JSONObject result = new JSONObject();
        result.put("comicName", comicName);
        result.put("comicId", comicId);
        result.put("comicThumbnail", comicThumbnail);
        result.put("comicSeries", comicSeries);
        result.put("comicSeriesId", comicSeriesId);
        result.put("comicGame", comicGame);
        result.put("comicGameAdd", comicGameAdd);
        result.put("comicGameEdit", comicGameEdit);
        result.put("comicGamePublic", comicGamePublic);
        result.put("comicGamePanelNo", comicGamePanelNo);
        result.put("comicGamePublished", comicGamePublished);
        result.put("comicClick", comicClick);
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
        List<GameComic> gameComics = gameComicService.getAllGameComics();


        List<String> comicName = new ArrayList<String>();
        List<Integer> comicId = new ArrayList<Integer>();
        List<String> comicThumbnail = new ArrayList<String>();
        List<Boolean> comicSeries = new ArrayList<Boolean>();
        List<Integer> comicSeriesId = new ArrayList<Integer>();
        List<Boolean> comicGame = new ArrayList<Boolean>();
        
        Integer comicType = 0;

        for(Panel p: panel){
            Boolean findFlag = false;
            if(p.getAuthor().equals(username)){
                for(RegularComic rc: regularComics){
                    for(Comic c: comics){
                        if(rc.getPanel_id().equals(p.getPanel_id()) && c.getComic_id().equals(rc.getRegular_comic_id()) && (c.getStatus() == 1 || c.getStatus() == 3)){
                            comicId.add(rc.getRegular_comic_id());
                            findFlag = true;
                        }
                    }
                }

                if(!findFlag){
                    for(GameComic gc: gameComics){
                        for(Comic c: comics){
                            Boolean panelMatch = checkMatch(p, gc);
                            if((c.getStatus() == 1 || c.getStatus() == 3) && panelMatch && gc.getGame_comic_id().equals( c.getComic_id())){
                                comicId.add(gc.getGame_comic_id()); 
                                findFlag = true;
                            }
                        }
                    }
                }

                if(!findFlag){
                    System.out.println("panel not found!");
                }

            }
        }

        Collections.sort(comicId);
        Collections.reverse(comicId);

        for(Integer i: comicId){
            for(Comic c: comics){
                if(c.getComic_id().equals(i)){
                    comicName.add(c.getTitle());
                    comicType = c.getComic_type();
                    break;
                }
            }

            if(comicType == 0){
                for(RegularComic rc: regularComics){
                    if(rc.getRegular_comic_id().equals( i)){
                        comicThumbnail.add(rc.getThumbnail_path());
                        comicSeries.add(rc.getSeries_id()==null?false:true);
                        comicSeriesId.add(rc.getSeries_id()==null?null:rc.getSeries_id());
                        comicGame.add(false);
                        break;
                    }
                }
            }else if(comicType == 1){
                GameComic gc = gameComicService.getGameComicByGameComicId(i);
                comicThumbnail.add(panelService.getPanelFromPanelId(gc.getPanel1_id()).getCanvas_path());
                comicSeries.add(false);
                comicSeriesId.add(null);
                comicGame.add(true);
            }
        }

        JSONObject result = new JSONObject();
        result.put("comicName", comicName);
        result.put("comicId", comicId);
        result.put("comicThumbnail", comicThumbnail);
        result.put("comicSeries", comicSeries);
        result.put("comicSeriesId", comicSeriesId);
        result.put("comicGame", comicGame);
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

            //NOTIFICATION SECTION
            List <Favorite> allFavorites = this.favoriteService.getAllFavorites();
            for(Favorite fav: allFavorites){
                if (fav.getComic_id() .equals( comicId)){
                    Date now = new Date();
                    int notification_type = 4;
                    String notification = "Favorite comic " + comic.getTitle() + " was deleted";
                    Notification note = new Notification();
                    note.setNotification_type(notification_type);
                    note.setNotification(notification);
                    note.setUsername(fav.getFavoriter_username());
                    note.setNotifcation_time(now);
                    this.notificationService.addNotification(note);
                }
            }
            //END NOTIFICATION SECTION

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

    @RequestMapping(value="/makePublic",method = RequestMethod.POST)
    @ResponseBody
    public Boolean makePublic(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        GameComic gc = gameComicService.getGameComicByGameComicId(comicId);
        gc.setGamecomic_type(2);
        gameComicService.addGameComic(gc);
        return true;
    }

    @RequestMapping(value="/setUser",method = RequestMethod.POST)
    @ResponseBody
    public void setUser(HttpServletRequest request){
        String game_Id = request.getParameter("gameId");
        Integer gameId = Integer.parseInt(game_Id);
        String username = request.getParameter("user");
        System.out.println(username+"!!!!!!!!!!!!");
        String position = request.getParameter("pos");
        Integer pos = Integer.parseInt(position);
        GameComic gc = gameComicService.getGameComicByGameComicId(gameId);
        if(username!=null&&!username.equals("")){
            Integer panelId = -1;
            if(pos==2){
                panelId = gc.getPanel2_id()==null? -1:gc.getPanel2_id();
            }else if(pos==3){
                panelId = gc.getPanel3_id()==null? -1:gc.getPanel3_id();
            }else if(pos==4){
                panelId = gc.getPanel4_id()==null? -1:gc.getPanel4_id();
            }
            if(panelId!=-1){
                Panel panel = panelService.getPanelFromPanelId(panelId);
                
                String oldUsername = panel.getAuthor();
                String newUsername = username;
                if(!panel.getAuthor().equals(username)){
                    panel.setAuthor(username);
                    panelService.addPanel(panel);
                }
                if(oldUsername.equals(newUsername) == false){
                    List<Notification> allNotes = this.notificationService.getAllNotifications();
                    for(Notification n: allNotes){
                        if(n.getUsername().equals(oldUsername)&&n.getLink().matches(gameId + " (.*)" + pos)){
                            n.setUsername(newUsername);
                            this.notificationService.addNotification(n);
                        }
                    }
                }
                // TODO: already create panel, need to delete old message, and add new message
                // gameId, pos = panelNo, oldUsername, newUsername

            }else{
                Panel addpanel = new Panel(username, null, null, new Date());
                panelService.addPanel(addpanel);
                if(pos==2){
                    gc.setPanel2_id(addpanel.getPanel_id());
                }
                if(pos==3){
                    gc.setPanel3_id(addpanel.getPanel_id());
                }
                if(pos==4){
                    gc.setPanel4_id(addpanel.getPanel_id());
                }
                gameComicService.addGameComic(gc);
                // TODO: new panel, add new message
                // gameId, pos = panelNo, username
                String inviter = this.panelService.getPanelFromPanelId(this.gameComicService.getGameComicByGameComicId(gameId).getPanel1_id()).getAuthor();
                Notification send = new Notification();
                send.setNotification_type(6);
                send.setLink(gameId + " " + pos);
                send.setUsername(username);
                send.setNotification(inviter + " has invited you to draw panel" + pos + " in the comicGame with id " + gameId);
                send.setNotifcation_time(new Date());
                this.notificationService.addNotification(send);
            }
        }else{
            Integer panelId = -1;
            if(pos==2){
                panelId = gc.getPanel2_id()==null? -1:gc.getPanel2_id();
                if(panelId.equals(gc.getPanel2_id())){
                    Integer t = null;
                    gc.setPanel2_id(t);
                }
            }
            if(pos==3){
                panelId = gc.getPanel3_id()==null? -1:gc.getPanel3_id();
                if(panelId.equals(gc.getPanel3_id())){
                    Integer t = null;
                    gc.setPanel3_id(t);
                }
            }
            if(pos==4){
                panelId = gc.getPanel4_id()==null? -1:gc.getPanel4_id();
                if(panelId.equals(gc.getPanel4_id())){
                    Integer t = null;
                    gc.setPanel4_id(t);
                }
            }
            if(panelId!=-1){
                Panel panel = panelService.getPanelFromPanelId(panelId);
                panelService.deletePanel(panel);
                // TODO: delete message
                // gameId, pos = panelNo, username
                System.out.println("message:" + gameId + "\t" + username + "\t" + pos);
                List<Notification>allNotes = this.notificationService.getAllNotifications();
                for(Notification x: allNotes){
                    if(x.getLink().matches(gameId + " (.*)" + pos) && x.getNotification_type() == 6){
                        this.notificationService.deleteNotification(x);
                    }
                }
            }
            gameComicService.addGameComic(gc);
        }

    }

    @RequestMapping(value="/list_invite_user",method = RequestMethod.POST)
    @ResponseBody
    public String list_invite_user(HttpServletRequest request){
        String game_comic_id = request.getParameter("game_comic_id");
        Integer gameId = Integer.parseInt(game_comic_id);
        GameComic gc = gameComicService.getGameComicByGameComicId(gameId);
        List<Boolean> finished = new ArrayList<>();
        List<String> username = new ArrayList<>();
        if(gc.getPanel2_id()!=null){
            Panel temp = panelService.getPanelFromPanelId(gc.getPanel2_id());
            if(temp.getCanvas_path()==null||temp.getCanvas_path().equals("")){
                finished.add(false);
                username.add(temp.getAuthor()==null?null:temp.getAuthor());
            }else{
                finished.add(true);
                username.add(temp.getAuthor());
            }
        }else{
            finished.add(false);
            username.add(null);
        }

        if(gc.getPanel3_id()!=null){
            Panel temp = panelService.getPanelFromPanelId(gc.getPanel3_id());
            if(temp.getCanvas_path()==null||temp.getCanvas_path().equals("")){
                finished.add(false);
                username.add(temp.getAuthor()==null?null:temp.getAuthor());
            }else{
                finished.add(true);
                username.add(temp.getAuthor());
            }
        }else{
            finished.add(false);
            username.add(null);
        }
        if(gc.getPanel4_id()!=null){
            Panel temp = panelService.getPanelFromPanelId(gc.getPanel4_id());
            if(temp.getCanvas_path()==null||temp.getCanvas_path().equals("")){
                finished.add(false);
                username.add(temp.getAuthor()==null?null:temp.getAuthor());
            }else{
                finished.add(true);
                username.add(temp.getAuthor());
            }
        }else{
            finished.add(false);
            username.add(null);
        }
        JSONObject result = new JSONObject();
        result.put("finished", finished);
        result.put("user", username);
        System.out.println(result.toString());
        return result.toString();
    }


    @RequestMapping(value="/checkUser",method = RequestMethod.POST)
    @ResponseBody
    public Boolean checkUser(HttpServletRequest request){
        String username = request.getParameter("username");
        return accountService.getAccountByUsername(username)==null?false:true;
    }

    public Boolean checkMatch(Panel p, GameComic gc){
        if (gc.getPanel1_id() != null)
            if (gc.getPanel1_id().equals(p.getPanel_id()))
                return true;

        if (gc.getPanel2_id() != null)
            if (gc.getPanel2_id().equals(p.getPanel_id()))
                return true;

        if (gc.getPanel3_id() != null)
            if (gc.getPanel3_id().equals(p.getPanel_id()))
                return true;

        if (gc.getPanel4_id() != null)
            if (gc.getPanel4_id().equals(p.getPanel_id()))
                return true;
        return false;
    }
}
