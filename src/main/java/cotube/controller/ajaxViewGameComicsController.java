package cotube.controller;


import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import java.util.Date;
import cotube.domain.Likes;
import cotube.services.LikesService;
import cotube.domain.Favorite;
import cotube.services.FavoriteService;
import cotube.domain.Comic;
import cotube.services.ComicService;
import cotube.domain.Comments;
import cotube.services.CommentsService;
import cotube.domain.Account;
import cotube.services.AccountService;
import cotube.domain.RegularComic;
import cotube.services.RegularComicService;
import cotube.domain.Panel;
import cotube.services.PanelService;
import cotube.domain.Tag;
import cotube.services.TagService;
import cotube.domain.FollowSeries;
import cotube.services.FollowSeriesService;
import cotube.domain.Views;
import cotube.services.ViewsService;
import cotube.domain.Folder;
import cotube.services.FolderService;
import cotube.services.SeriesService;
import java.util.ArrayList;
import java.util.Collections;

// TODO: Check everything works
@Controller
@RequestMapping(value="/viewGameComics.html")
public class ajaxViewGameComicsController{

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
            if(each.getComic_id()==Integer.parseInt(comicid)){
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
            if(each.getRegular_comic_id()==Integer.parseInt(comicid)){
                comicpanel = each.getPanel_id();
                description = each.getDescription();
                break;
            }
        }
        List<Panel> panels = panelService.getAllPanels();
        for(Panel each : panels){
            if(each.getPanel_id()==comicpanel){
                author = each.getAuthor();
                path = each.getCanvas_path();
                break;
            }
        }
        List<Views> views = viewsService.getAllViews();
        int viewscount = 0;
        for(Views each : views){
            if(each.getComic_id()==Integer.parseInt(comicid)){
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
            if(each.getComic_id()==Integer.parseInt(comicid)&&each.getLiker_username().equals(username)){
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
            if(each.getComic_id()==Integer.parseInt(comicid)){
                count++;
            }
        }
        return count;
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
                if(each.getComic_id()==Integer.parseInt(comicid)&&each.getLiker_username().equals(username)){
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
        c.setStatus(0);
        c.setUsername(username);
        for(Comments co: comments){
            if(co.getComic_id() == comicid){
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
            if(c.getComic_id() == comicId && c.getComment_number() == num){
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
            if(c.getStatus()!=1 && c.getComic_id() == comicid){
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
                if(c.getComment_number() == n && c.getComic_id() == comicid){
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



}
