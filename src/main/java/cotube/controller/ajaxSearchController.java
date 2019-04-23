package cotube.controller;

import cotube.domain.*;
import cotube.services.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(value="/searchResult.html")
public class ajaxSearchController{

    private FollowUserService followUserService;
    @Autowired
    public void setFollowUserService(FollowUserService followUserService) {
        this.followUserService = followUserService;
    }

    private SeriesService seriesService;
    @Autowired
    public void setSeriesService(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    private RegularComicService regularComicService;
    @Autowired
    private void setRegularComicService(RegularComicService regularComicService){
        this.regularComicService = regularComicService;
    }
    private PanelService panelService;
    @Autowired
    public void setPanelService(PanelService panelService) {
        this.panelService = panelService;
    }

    private AccountService accountService;
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    private ComicService comicService;
    @Autowired
    public void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }

    private LikesService likeService;
    @Autowired
    public void setLikesService(LikesService likeService) {
        this.likeService = likeService;
    }

    private ViewsService viewsService;
    @Autowired
    public void setViewsService(ViewsService viewService) {
        this.viewsService = viewService;
    }

    @RequestMapping(value="/author",method = RequestMethod.POST)
    @ResponseBody
    public String searchByAuthor(HttpServletRequest request){
        String author = request.getParameter("author");
        System.out.println(author);
        List<Account> accounts = accountService.getAllAccounts();

        List<Integer> followerCount = new ArrayList<Integer>();
        List<String> matches = new ArrayList<String>();
        List<String> pic = new ArrayList<String>();
        for(Account acc: accounts){
            if (acc.getUsername().contains(author)&&acc.getAccount_role()==0){
                followerCount.add(followUserService.getFollowerCount(acc.getUsername()));
                matches.add(acc.getUsername());
                pic.add(acc.getProfile_pic_path());
            }
        }
        for(int i=0;i<followerCount.size();i++){
            System.out.println(i+1 + ": " + matches.get(i) + "\t" + followerCount.get(i) + "\t" + pic.get(i));
        }
        JSONObject result = new JSONObject();
        result.put("account", matches);
        result.put("followers", followerCount);
        result.put("picpath", pic);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/keyword",method = RequestMethod.POST)
    @ResponseBody
    public String searchByKeyword(HttpServletRequest request){

        String keyword = request.getParameter("keyword");
        List<Comic> comics = this.comicService.searchComicsByTitle(keyword);
        List<String>titles = new ArrayList<String>();
        List<String>picPath = new ArrayList<String>();
        List<String>authors = new ArrayList<String>();
        List<Integer>likes = new ArrayList<Integer>();
        List<Integer>views = new ArrayList<Integer>();
        List<Integer>IDs = new ArrayList<>();
        List<RegularComic>regularComics = this.regularComicService.getAllRegularComics();
        for (Comic c: comics){
            titles.add(c.getTitle());
            IDs.add(c.getComic_id());
            for (RegularComic reg: regularComics){
                if (reg.getRegular_comic_id() == c.getComic_id()){
                    picPath.add(reg.getThumbnail_path());
                    authors.add(getAuthor(reg));
                    likes.add(getLikes(reg));
                    views.add(getViews(reg));
                }
            }
        }
        List<searchPackage>result = new ArrayList<>();
        for (int i = 0;i < titles.size(); i++){
            searchPackage packed = new searchPackage(titles.get(i),picPath.get(i),authors.get(i),likes.get(i),views.get(i),IDs.get(i));
                result.add(packed);
        }
        String sortMethod = request.getParameter("sorted");
        Comparator<searchPackage> compareByViews = (searchPackage o1, searchPackage o2) ->
                Integer.compare(o1.getViews(), o2.getViews());
        Comparator<searchPackage> compareByLikes = (searchPackage o1, searchPackage o2) ->
                Integer.compare(o1.getLikes(), o2.getLikes());
        Comparator<searchPackage> compareByAlphabet = (searchPackage o1, searchPackage o2) ->
                o1.getTitle().compareTo(o2.getTitle());
        System.out.println(sortMethod);
        if(sortMethod.equals("1")) {
            Collections.sort(result, compareByViews);
            Collections.reverse(result);
        }
        else if(sortMethod.equals("2")){
            Collections.sort(result, compareByLikes);
            //System.out.println("RESULT;");
            //System.out.println(result);
            Collections.reverse(result);
        }
        else if(sortMethod.equals("3")){
            Collections.sort(result, compareByAlphabet);
        }
        JSONObject go = new JSONObject();
        go.put("TPALV",result);
        //System.out.println(go.toString());
        return go.toString();
    }
    private String getAuthor(RegularComic reg){
        List<Panel>panels = this.panelService.getAllPanels();
        for (Panel p: panels){
            if (reg.getRegular_comic_id() == p.getPanel_id()){
                return p.getAuthor();
            }
        }
        return "NO AUTHOR";
    }
    private int getLikes(RegularComic reg){
        List<Likes>likes = this.likeService.getAllLikes();
        int total = 0;
        for (Likes like:likes){
            if (like.getComic_id() == reg.getRegular_comic_id()){
                total++;
            }
        }
        return total;
    }
    private int getViews(RegularComic reg){
        List<Views>views = this.viewsService.getAllViews();
        int total = 0;
        for (Views view:views){
            if (view.getComic_id() == reg.getRegular_comic_id()){
                total++;
            }
        }
        return total;
    }

    @RequestMapping(value="/seriesSearch",method = RequestMethod.POST)
    @ResponseBody
    public String seriesSearch(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        List<String>titles = new ArrayList<String>();
        List<String>picPath = new ArrayList<String>();
        List<Integer>ID = new ArrayList<>();
        List<Integer>totalComics = new ArrayList<>();
        List<Series>all = this.seriesService.getAllSeries();
        for(Series x: all){
            if (x.getSeries_name().contains(keyword)){
                titles.add(x.getSeries_name());
                picPath.add(x.getSeries_thumbnail_path());
                ID.add(x.getSeries_id());
            }
        }
        List<RegularComic>regComics = this.regularComicService.getAllRegularComics();
        for(Integer x: ID){
            int total = 0;
            for(RegularComic reg: regComics){
                if(reg.getSeries_id() == x){
                    System.out.println(reg.getSeries_id());
                    total = total + 1;
                }
                totalComics.add(total);
            }
        }
        ArrayList<seriesSearchPackage>result = new ArrayList<seriesSearchPackage>();
        for(int i = 0;i<titles.size();i++){
            seriesSearchPackage add = new seriesSearchPackage(titles.get(i),picPath.get(i),"user1",ID.get(i),totalComics.get(i));
            result.add(add);
        }
        JSONObject go = new JSONObject();
        go.put("TPALV",result);
        //System.out.println(go.toString());
        return go.toString();
    }

}
