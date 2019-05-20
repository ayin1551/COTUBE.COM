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
import cotube.domain.Comic;
import cotube.services.AccountService;
import cotube.services.ComicService;
import cotube.domain.Comments;
import cotube.services.CommentsService;
import cotube.services.PanelService;
import cotube.services.GameComicService;
import cotube.domain.GameComic;
import cotube.domain.Views;
import cotube.services.ViewsService;
import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequestMapping(value="/viewGameComics.html")
public class ajaxViewGameComicsController{

    private LikesService likesService;
    @Autowired
    public void setLikesService(LikesService likesService) {
        this.likesService = likesService;
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

    private ViewsService viewsService;
    @Autowired
    public void setViewsService(ViewsService viewsService) {
        this.viewsService = viewsService;
    }

    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private GameComicService gameComicService;
    @Autowired
    public void setGameComicService(GameComicService gameComicService) {
        this.gameComicService = gameComicService;
    }


    @RequestMapping(value="/comicInfo",method = RequestMethod.POST)
    @ResponseBody
    public String comicInfo(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comic_id"));
        String username = request.getParameter("username");
        Comic c = comicService.getComicByComic_Id(comicId);
        GameComic gc = gameComicService.getGameComicByGameComicId(comicId);
        String title = c.getTitle();
        String author1 = panelService.getPanelFromPanelId(gc.getPanel1_id()).getAuthor();
        String author2 = panelService.getPanelFromPanelId(gc.getPanel2_id()).getAuthor();
        String author3 = panelService.getPanelFromPanelId(gc.getPanel3_id()).getAuthor();
        String author4 = panelService.getPanelFromPanelId(gc.getPanel4_id()).getAuthor();
        String authorPath1 = accountService.getAccountByUsername(author1).getProfile_pic_path();
        String authorPath2 = accountService.getAccountByUsername(author2).getProfile_pic_path();
        String authorPath3 = accountService.getAccountByUsername(author3).getProfile_pic_path();
        String authorPath4 = accountService.getAccountByUsername(author4).getProfile_pic_path();
        String path1 = panelService.getPanelFromPanelId(gc.getPanel1_id()).getCanvas_path();
        String path2 = panelService.getPanelFromPanelId(gc.getPanel2_id()).getCanvas_path();
        String path3 = panelService.getPanelFromPanelId(gc.getPanel3_id()).getCanvas_path();
        String path4 = panelService.getPanelFromPanelId(gc.getPanel4_id()).getCanvas_path();
        Integer views = viewsService.getAllViewsInComic(comicId).size();
        Integer likes = likesService.getAllLikesInComic(comicId).size();
        Boolean ifLiked = false;
        List<Likes> allLikes = likesService.getAllLikesInComic(comicId);
        for(Likes l: allLikes){
            if(l.getLiker_username().equals(username)){
                ifLiked = true;
                break;
            }
        }

        JSONObject result = new JSONObject();
        result.put("title",title);
        result.put("author1", author1);
        result.put("author2", author2);
        result.put("author3", author3);
        result.put("author4", author4);
        result.put("authorPath1", authorPath1);
        result.put("authorPath2", authorPath2);
        result.put("authorPath3", authorPath3);
        result.put("authorPath4", authorPath4);
        result.put("path1", path1);
        result.put("path2", path2);
        result.put("path3", path3);
        result.put("path4", path4);
        result.put("views", views);
        result.put("likes", likes);
        result.put("ifLiked", ifLiked);
        return result.toString();
    }

    @RequestMapping(value="/toggleLike",method = RequestMethod.POST)
    @ResponseBody
    public Integer toggleLike(HttpServletRequest request){
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
        return likesService.getAllLikesInComic(Integer.parseInt(comicid)).size();
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

    @RequestMapping(value="/gameExist",method = RequestMethod.POST)
    @ResponseBody
    public Boolean gameExist(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        List<GameComic> gameComics = gameComicService.getAllGameComics();
        
        for(GameComic gc: gameComics){
            if(gc.getGame_comic_id().equals(comicId) && (comicService.getComicByComic_Id(gc.getGame_comic_id()).getStatus()==1 ||comicService.getComicByComic_Id(gc.getGame_comic_id()).getStatus()==3)){
                return true;
            }
        }
        return false;
    }

}
