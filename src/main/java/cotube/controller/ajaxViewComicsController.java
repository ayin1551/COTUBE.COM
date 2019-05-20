package cotube.controller;


import cotube.domain.*;
import cotube.services.*;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import java.util.Date;

import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequestMapping(value="/viewComics.html")
public class ajaxViewComicsController{
    private NotificationService notificationService;
    @Autowired
    public void setNotificationService(NotificationService notificationService){
        this.notificationService = notificationService;
    }
    private LikesService likesService;
    @Autowired
    public void setLikesService(LikesService likesService) {
        this.likesService = likesService;
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

    private PanelService panelService;
    @Autowired
    public void setPanelService(PanelService panelService) {
        this.panelService = panelService;
    }

    private TagService tagService;
    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    private FollowSeriesService followSeriesService;
    @Autowired
    public void setFollowSeriesService(FollowSeriesService followSeriesService) {
        this.followSeriesService = followSeriesService;
    }


    private ViewsService viewsService;
    @Autowired
    public void setViewsService(ViewsService viewsService) {
        this.viewsService = viewsService;
    }
    
    private RegularComicService regularComicService;
    @Autowired
    public void setRegularComicService(RegularComicService regularComicService) {
        this.regularComicService = regularComicService;
    }

    private FolderService folderService;
    @Autowired
    public void setFolderService(FolderService folderService) {
        this.folderService = folderService;
    }

    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private SeriesService seriesService;
    @Autowired
    public void setSeriesService(SeriesService seriesService) {
        this.seriesService = seriesService;
    }


    @RequestMapping(value="/comicTitle",method = RequestMethod.POST)
    @ResponseBody
    public String comicTitle(HttpServletRequest request){
        String comicid = request.getParameter("comic_id");
        System.out.println(comicid);
        String title = "";
        List<Comic> comics = comicService.getAllComics();
        for(Comic each : comics){
            if(each.getComic_id().equals(Integer.parseInt(comicid))){
                title = each.getTitle();
                break;
            }
        }
        return title;
    }

    @RequestMapping(value="/comicInfo",method = RequestMethod.POST)
    @ResponseBody
    public String comicInfo(HttpServletRequest request){
        String comicid = request.getParameter("comic_id");
        System.out.println(comicid);
        int comicpanel = -1;
        String description = "";
        String author = "";
        String path = "";
        List<RegularComic> comics = regularComicService.getAllRegularComics();
        for(RegularComic each : comics){
            if(each.getRegular_comic_id().equals(Integer.parseInt(comicid))){
                comicpanel = each.getPanel_id();
                description = each.getDescription();
                break;
            }
        }
        List<Panel> panels = panelService.getAllPanels();
        for(Panel each : panels){
            if(each.getPanel_id().equals(comicpanel)){
                author = each.getAuthor();
                path = each.getCanvas_path();
                break;
            }
        }
        List<Views> views = viewsService.getAllViews();
        int viewscount = 0;
        for(Views each : views){
            if(each.getComic_id().equals(Integer.parseInt(comicid))){
                viewscount++;
            }
        }
        String authorpath = "";
        List<Account> users = accountService.getAllAccounts();
        for(Account each : users){
            if(each.getUsername().equals(author)){
                authorpath = each.getProfile_pic_path();
            }
        }
        JSONObject result = new JSONObject();
        result.put("author", author);
        result.put("path", path);
        result.put("authorpath", authorpath);
        result.put("views", viewscount);
        result.put("description", description);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/checkLike",method = RequestMethod.POST)
    @ResponseBody
    public Boolean checkLike(HttpServletRequest request){
        String username = request.getParameter("username");
        String comicid = request.getParameter("comic_id");
        System.out.println(username);
        System.out.println(comicid);
        List<Likes> likes = likesService.getAllLikes();
        for(Likes each : likes){
            if(each.getComic_id().equals(Integer.parseInt(comicid))&&each.getLiker_username().equals(username)){
                return true;
            }
        }

        return false;
    }

    @RequestMapping(value="/likeNumber",method = RequestMethod.POST)
    @ResponseBody
    public int likeNumber(HttpServletRequest request){
        String comicid = request.getParameter("comic_id");
        System.out.println(comicid);
        int count = 0;
        List<Likes> likes = likesService.getAllLikes();
        for(Likes each : likes){
            if(each.getComic_id().equals(Integer.parseInt(comicid))){
                count++;
            }
        }
        return count;
    }


    @RequestMapping(value="/checkFavorite",method = RequestMethod.POST)
    @ResponseBody
    public Boolean checkFavorite(HttpServletRequest request){
        String username = request.getParameter("username");
        String comicid = request.getParameter("comic_id");
        System.out.println(username);
        System.out.println(comicid);
        List<Favorite> favorites = favoriteService.getAllFavorites();
        for(Favorite each : favorites){
            if(each.getComic_id().equals(Integer.parseInt(comicid))&&each.getFavoriter_username().equals(username)){
                return true;
            }
        }

        return false;
    }


    @RequestMapping(value="/toggleLike",method = RequestMethod.POST)
    @ResponseBody
    public Boolean toggleLike(HttpServletRequest request){
        String username = request.getParameter("username");
        String comicid = request.getParameter("comic_id");
        Boolean like = request.getParameter("like").equals("true")?true:false;
        List<Likes> all = likesService.getAllLikes();
        if(like){
            for(Likes each : all){
                if(each.getComic_id().equals(Integer.parseInt(comicid))&&each.getLiker_username().equals(username)){
                    likesService.deleteLike(each);
                    break;
                }
            }
        }else{
            Likes add = new Likes(Integer.parseInt(comicid), username, new Date());
            likesService.addLike(add);
        }

        return true;
    }

    @RequestMapping(value="/removeFromFav",method = RequestMethod.POST)
    @ResponseBody
    public Boolean removeFromFav(HttpServletRequest request){
        String username = request.getParameter("username");
        String comicid = request.getParameter("comic_id");
        List<Favorite> favorites = favoriteService.getAllFavorites();
        for(Favorite each : favorites){
            if(each.getComic_id().equals(Integer.parseInt(comicid))&&each.getFavoriter_username().equals(username)){
                favoriteService.deleteFavorite(each);
            }
        } 
        return true;
    }

    @RequestMapping(value="/listFavorite",method = RequestMethod.POST)
    @ResponseBody
    public String listFavorite(HttpServletRequest request){
        String username = request.getParameter("username");
        List<Folder> favoritesfolder = folderService.getAllFolders();
        List<String> folderName = new ArrayList<String>();
        List<Integer> folderId = new ArrayList<Integer>();
        for(Folder each : favoritesfolder){
            if(each.getUsername().equals(username)&&each.getFolder_type()==0){
                folderName.add(each.getFolder_name());
                folderId.add(each.getFolder_id());
            }
        }
        System.out.println("favorite list:");
        for(int i=0;i<folderName.size();i++){
            System.out.println(folderName.get(i));
        }
        JSONObject result = new JSONObject();
        result.put("name", folderName);
        result.put("id", folderId);
        return result.toString();
    }

    @RequestMapping(value="/addToFav",method = RequestMethod.POST)
    @ResponseBody
    public String addToFav(HttpServletRequest request){
        String username = request.getParameter("username");
        String comicid = request.getParameter("comic_id");
        String newlist= request.getParameter("new_list");
        String idlist= request.getParameter("id_list");
        System.out.println("newlist: " + newlist);
        System.out.println("idlist: " + idlist);
        if(!newlist.equals("")&&newlist!=null){
            // create new folder, then add comic into folder
            int i = 0;
            List<Folder> all = folderService.getAllFolders();
            for(Folder a : all){
                if(a.getFolder_id()>i){
                    i = a.getFolder_id();
                }
            }
            Folder newf = new Folder(username,newlist,0,1);
            folderService.addFolder(newf);
            System.out.println(newf);
            System.out.println(newf.getFolder_id());
            Favorite newfa = new Favorite(Integer.parseInt(comicid), username, new Date(), newf.getFolder_id());
            favoriteService.addFavorite(newfa);
        }
        if(idlist!=null&&!idlist.equals("")){
            String[] id = idlist.split(",");
            for(String a : id){
                Favorite newfa = new Favorite(Integer.parseInt(comicid), username, new Date(), Integer.parseInt(a));
                favoriteService.addFavorite(newfa);
            }
        }
        return "";
    }


    @RequestMapping(value="/postComment",method = RequestMethod.POST)
    @ResponseBody
    public Boolean postComment(HttpServletRequest request){
        String username = request.getParameter("username");
        Integer comicid = Integer.parseInt(request.getParameter("comic_id"));
        String comment = request.getParameter("comment");
        List<Comments> comments = this.commentsService.getAllComments();
        Integer high = 0;
        Comments c = new Comments();
        c.setComic_id(comicid);
        c.setComment(comment);
        c.setComment_time(new Date());
        c.setStatus(2);
        c.setUsername(username);
        for(Comments co: comments){
            if(co.getComic_id().equals(comicid)){
                if(co.getComment_number()>high){
                    high = co.getComment_number();
                }
            }
        }
        c.setComment_number(high+1);
        commentsService.addComments(c);
        return true;
    }

    @RequestMapping(value="/deleteComment",method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteComment(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        Integer num = Integer.parseInt(request.getParameter("num"));
        List<Comments> comments = this.commentsService.getAllComments();
        for(Comments c: comments){
            if(c.getComic_id().equals(comicId) && c.getComment_number().equals(num)){
                this.commentsService.deleteComment(c);
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value="/getComment",method = RequestMethod.POST)
    @ResponseBody
    public String getComment(HttpServletRequest request){
        Integer comicid = Integer.parseInt(request.getParameter("comicId"));
        Integer num = Integer.parseInt(request.getParameter("num"));
        List<Comments> comments = this.commentsService.getAllComments();
        List<String> commentContent = new ArrayList<String>();
        List<String> commenter = new ArrayList<String>();
        List<String> commentTime = new ArrayList<String>();
        List<Integer> commentNumber = new ArrayList<Integer>();
        double count = 0;

        for(Comments c: comments){
            if(c.getStatus()!=1 && c.getComic_id().equals(comicid)){
                commentNumber.add(c.getComment_number());
                count += 1;
            }
        }

        Collections.sort(commentNumber);
        Collections.reverse(commentNumber);

        if(commentNumber.size() < 20 * (num-1)){

        }else if(commentNumber.size() <= 20 * num){
            commentNumber = commentNumber.subList(20*(num-1), commentNumber.size());
        }else if(commentNumber.size() > 20 * num){
            commentNumber = commentNumber.subList(20*(num-1), 20 * num);
        }
        for(Integer n: commentNumber){
            for(Comments c: comments){
                if(c.getComment_number().equals(n) && c.getComic_id().equals(comicid)){
                    commentContent.add(c.getComment());
                    commenter.add(c.getCommenter_username());
                    commentTime.add(c.getComment_time().toString());
                }
            }
        }

        JSONObject result = new JSONObject();
        result.put("commentNumber", commentNumber);
        result.put("commentContent", commentContent);
        result.put("commenter", commenter);
        result.put("commentTime", commentTime);
        result.put("commentCount", count);
        result.put("commentPage", Math.ceil(count/20));
        System.out.println(result.toString());
        return result.toString();


    }

    @RequestMapping(value="/hasPrev",method = RequestMethod.POST)
    @ResponseBody
    public Boolean hasPrev(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        List<Integer> series = new ArrayList<Integer>();
        Integer seriesId = 0;
        
        for(RegularComic rc: regularComics){
            if(rc.getRegular_comic_id().equals(comicId)){
                seriesId = rc.getSeries_id();
                break;
            }
        }

        for(RegularComic rc: regularComics){
            if(rc.getSeries_id()!=null && rc.getSeries_id().equals(seriesId)){
                series.add(rc.getRegular_comic_id());
            }
        }

        Collections.sort(series);
        if(series.get(0).equals(comicId)){
            return false;
        }


        return true;
    }

    @RequestMapping(value="/hasNext",method = RequestMethod.POST)
    @ResponseBody
    public Boolean hasNext(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        List<Integer> series = new ArrayList<Integer>();
        Integer seriesId = 0;
        
        for(RegularComic rc: regularComics){
            if(rc.getRegular_comic_id().equals(comicId)){
                seriesId = rc.getSeries_id();
                break;
            }
        }

        for(RegularComic rc: regularComics){
            if(rc.getSeries_id()!=null && rc.getSeries_id().equals(seriesId) && (comicService.getComicByComic_Id(rc.getRegular_comic_id()).getStatus()==1 || comicService.getComicByComic_Id(rc.getRegular_comic_id()).getStatus()==3)){
                series.add(rc.getRegular_comic_id());
            }
        }

        Collections.sort(series);
        if(series.get(series.size()-1).equals(comicId)){
            return false;
        }


        return true;
    }

    @RequestMapping(value="/prev",method = RequestMethod.POST)
    @ResponseBody
    public Integer prev(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        List<Integer> series = new ArrayList<Integer>();
        Integer seriesId = 0;
        
        for(RegularComic rc: regularComics){
            if(rc.getRegular_comic_id().equals(comicId)){
                seriesId = rc.getSeries_id();
                break;
            }
        }

        for(RegularComic rc: regularComics){
            if(rc.getSeries_id()!=null && rc.getSeries_id().equals(seriesId)){
                series.add(rc.getRegular_comic_id());
            }
        }

        Collections.sort(series);
        for(int i = 1; i < series.size(); i++){
            if(series.get(i).equals(comicId)){
                return series.get(i-1);
            }
        }
       
        return null;
    }

    @RequestMapping(value="/next",method = RequestMethod.POST)
    @ResponseBody
    public Integer next(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        List<Integer> series = new ArrayList<Integer>();
        Integer seriesId = 0;
        
        for(RegularComic rc: regularComics){
            if(rc.getRegular_comic_id().equals(comicId)){
                seriesId = rc.getSeries_id();
                break;
            }
        }

        for(RegularComic rc: regularComics){
            if(rc.getSeries_id()!=null && rc.getSeries_id() .equals( seriesId)){
                series.add(rc.getRegular_comic_id());
            }
        }

        Collections.sort(series);
        for(int i = 0; i < series.size(); i++){
            if(series.get(i).equals(comicId)){
                return series.get(i+1);
            }
        }


        return null;
    }


    @RequestMapping(value="/viewComic",method = RequestMethod.POST)
    @ResponseBody
    public Integer viewComic(HttpServletRequest request){
        String username = request.getParameter("username");
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        List<Views> views = viewsService.getAllViewsInComic(comicId);
        Boolean ifView = false;

        for(Views v: views){    
            if((v.getViewer_username().equals(username))){
                ifView = true;
                break;
            }
        }

        if(!ifView){
            if (username ==  null){
                System.out.println("Guest viewing comic");
                StringBuilder sb = new StringBuilder();
                sb.append("Guest");
                String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                        + "0123456789"
                        + "abcdefghijklmnopqrstuvxyz";
                for (int i = 0; i < 8; i++) {

                    // generate a random number between
                    // 0 to AlphaNumericString variable length
                    int index
                            = (int)(AlphaNumericString.length()
                            * Math.random());

                    // add Character one by one in end of sb
                    sb.append(AlphaNumericString
                            .charAt(index));
                }
                username = sb.toString();
                System.out.println(username);
            }

            Views v = new Views();
            v.setComic_id(comicId);
            v.setViewer_username(username);
            v.setView_time(new Date());
            this.viewsService.addView(v);
        }


        return null;
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
                if (fav.getComic_id() .equals(comicId)){
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

    @RequestMapping(value="/comicExist",method = RequestMethod.POST)
    @ResponseBody
    public Boolean comicExist(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        
        for(RegularComic rc: regularComics){
            if(rc.getRegular_comic_id().equals(comicId) && (comicService.getComicByComic_Id(rc.getRegular_comic_id()).getStatus()==1 ||comicService.getComicByComic_Id(rc.getRegular_comic_id()).getStatus()==3)){
                return true;
            }
        }
        return false;
    }
    
    @RequestMapping(value="/comicExistProfile",method = RequestMethod.POST)
    @ResponseBody
    public Boolean comicExistProfile(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        
        for(RegularComic rc: regularComics){
            if(rc.getRegular_comic_id().equals(comicId)){
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value="/comicExistAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Boolean comicExistAdmin(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        
        for(RegularComic rc: regularComics){
            if(rc.getRegular_comic_id().equals(comicId) && (comicService.getComicByComic_Id(rc.getRegular_comic_id()).getStatus()!=0)){
                return true;
            }
        }
        return false;
    }



}
