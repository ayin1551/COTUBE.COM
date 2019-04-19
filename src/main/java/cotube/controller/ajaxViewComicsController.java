package cotube.controller;


import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import cotube.domain.Likes;
import cotube.services.LikesService;
import cotube.domain.Favorite;
import cotube.services.FavoriteService;
import cotube.domain.Comic;
import cotube.services.AccountService;
import cotube.services.ComicService;
import cotube.domain.RegularComic;
import cotube.services.RegularComicService;
import cotube.domain.Panel;
import cotube.services.PanelService;
import cotube.domain.Views;
import cotube.services.ViewsService;
import cotube.domain.Account;
import cotube.services.AccountService;

@Controller
@RequestMapping(value="/viewComics.html")
public class ajaxViewComicsController{

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
    
    private RegularComicService regularComicService;
    @Autowired
    public void setRegularComicService(RegularComicService regularComicService) {
        this.regularComicService = regularComicService;
    }

    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
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


    @RequestMapping(value="/checkFavorite",method = RequestMethod.POST)
    @ResponseBody
    public Boolean checkFavorite(HttpServletRequest request){
        String username = request.getParameter("username");
        String comicid = request.getParameter("comic_id");
        System.out.println(username);
        System.out.println(comicid);
        List<Favorite> favorites = favoriteService.getAllFavorites();
        for(Favorite each : favorites){
            if(each.getComic_id()==Integer.parseInt(comicid)&&each.getFavoriter_username().equals(username)){
                return true;
            }
        }

        return false;
    }



    // TODO: like/unlike
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
