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
    private FolderService folderService;
    @Autowired
    public void setFolderService(FolderService folderService){
        this.folderService = folderService;
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

    private GameComicService gameService;
    @Autowired
    public void setViewsService(GameComicService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value="/author",method = RequestMethod.POST)
    @ResponseBody
    public String searchByAuthor(HttpServletRequest request){
        String author = request.getParameter("author");
        String page = request.getParameter("page");
        System.out.println(author);
        List<Account> accounts = accountService.getAllAccounts();

        List<Integer> followerCount = new ArrayList<Integer>();
        List<String> matches = new ArrayList<String>();
        List<String> pic = new ArrayList<String>();
        int count = 0;
        for(Account acc: accounts){
            if (acc.getUsername().contains(author)&&acc.getAccount_role()==0){
                followerCount.add(followUserService.getFollowerCount(acc.getUsername()));
                matches.add(acc.getUsername());
                pic.add(acc.getProfile_pic_path());
                count++;
            }
        }
        count = followerCount.size();
        System.out.println("count：" + count);
        // for(int i=0;i<followerCount.size();i++){
        //     System.out.println(i+1 + ": " + matches.get(i) + "\t" + followerCount.get(i) + "\t" + pic.get(i));
        // }
        int pageint = Integer.parseInt(page);
        if((Integer.parseInt(page))*12>matches.size()){
            System.out.print("-----------in if---------");
            followerCount = followerCount.subList((pageint-1)*12,count);
            matches = matches.subList((pageint-1)*12, count);
            pic = pic.subList((pageint-1)*12, count);
        }else{
            followerCount = followerCount.subList((pageint-1)*12,pageint*12);
            matches = matches.subList((pageint-1)*12, pageint*12);
            pic = pic.subList((pageint-1)*12, pageint*12);
        }
        JSONObject result = new JSONObject();
        result.put("account", matches);
        result.put("followers", followerCount);
        result.put("picpath", pic);
        result.put("totalpage", Math.ceil(count/12.0));
        result.put("pagenumber", pageint);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/keyword",method = RequestMethod.POST)
    @ResponseBody
    public String searchByKeyword(HttpServletRequest request){
        String keyword = request.getParameter("keyword");
        String pagenum1 = request.getParameter("page");
        System.out.println("!!!!!!!!!!!!!!" + keyword + "\t" + pagenum1);
        int pagenum = Integer.parseInt(pagenum1);
        List<Comic> comics = this.comicService.searchComicsByTitlePublic(keyword);
        List<String>titles = new ArrayList<String>();
        List<String>picPath = new ArrayList<String>();
        List<String>authors = new ArrayList<String>();
        List<Integer>likes = new ArrayList<Integer>();
        List<Integer>views = new ArrayList<Integer>();
        List<Integer>IDs = new ArrayList<>();
        List<Boolean>isSeries = new ArrayList<>();
        List<RegularComic>regularComics = this.regularComicService.getAllRegularComics();
        for (Comic c: comics){
            if((c.getStatus()==1||c.getStatus()==3) && c.getComic_type()==0){
                titles.add(c.getTitle());
                IDs.add(c.getComic_id());
                for (RegularComic reg: regularComics){
                    if (reg.getRegular_comic_id().equals( c.getComic_id())){
                        picPath.add(reg.getThumbnail_path());
                        authors.add(getAuthor(reg.getPanel_id()));
                        likes.add(getLikes(reg));
                        views.add(getViews(reg));
                        if(reg.getSeries_id()==null){
                            isSeries.add(false);
                        }else{
                            isSeries.add(true);
                        }
                    }
                }
            }
        }
        // System.out.println(IDs);
        // System.out.println(isSeries);

        List<searchPackage>result = new ArrayList<>();
        for (int i = 0;i < authors.size(); i++){
            System.out.println(authors.size());
            System.out.println(IDs.size());
            searchPackage packed = new searchPackage(titles.get(i),picPath.get(i),authors.get(i),likes.get(i),views.get(i),IDs.get(i),isSeries.get(i));
            result.add(packed);
            // System.out.println(packed.getisSeries());
        }
        // System.out.println(result);
        // System.out.println(result.toString());
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
        int count = result.size();
        if(pagenum*15>result.size()){
            System.out.print("-----------in if regular---------");
            result = result.subList((pagenum-1)*15,count);
        }else{
            result = result.subList((pagenum-1)*15,pagenum*15);
        }
        List<Boolean> ifSeries = new ArrayList<>();
        for(int i=0;i<result.size();i++){
            ifSeries.add(result.get(i).getisSeries());
        }
        JSONObject go = new JSONObject();
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i).getComicID() + "\t" + result.get(i).getisSeries());
        }
        go.put("TPALV",result);
        go.put("isSeries",ifSeries);
        go.put("pagenumber",pagenum);
        go.put("totalpage",Math.ceil(count/15.0));
        // System.out.println(go.toString());
        return go.toString();
    }
    private String getAuthor(Integer id){
        Panel panels = panelService.getPanelFromPanelId(id);
        // for (Panel p: panels){
        //     if (reg.getRegular_comic_id() == p.getPanel_id()){
                return panels.getAuthor()==null?"NO AUTHOR":panels.getAuthor();
        //     }
        // }
        // return "NO AUTHOR";
    }
    private int getLikes(RegularComic reg){
        List<Likes>likes = this.likeService.getAllLikes();
        int total = 0;
        for (Likes like:likes){
            if (like.getComic_id().equals( reg.getRegular_comic_id())){
                total++;
            }
        }
        return total;
    }
    private int getViews(RegularComic reg){
        List<Views>views = this.viewsService.getAllViews();
        int total = 0;
        for (Views view:views){
            if (view.getComic_id().equals(reg.getRegular_comic_id())){
                total++;
            }
        }
        return total;
    }

    @RequestMapping(value="/seriesSearch",method = RequestMethod.POST)
    @ResponseBody
    public String seriesSearch(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        String pagenum1 = request.getParameter("page");
        System.out.println("!!!!!!!!!!!!!!" + keyword + "\t" + pagenum1);
        int pagenum = Integer.parseInt(pagenum1);
        List<String>titles = new ArrayList<String>();
        List<String>authors = new ArrayList<String>();
        List<String>picPath = new ArrayList<String>();
        List<Integer>ID = new ArrayList<>();
        List<Integer>totalComics = new ArrayList<>();
        List<Series>all = this.seriesService.getAllSeries();
        for(Series x: all){
            if (x.getSeries_name().toUpperCase().contains(keyword.toUpperCase())){
                titles.add(x.getSeries_name());
                picPath.add(x.getSeries_thumbnail_path());
                ID.add(x.getSeries_id());
                authors.add(getAuthorbyFolderID(x.getFolder_id()));
            }
        }
        List<RegularComic>regComics = this.regularComicService.getAllRegularComics();
        for(Integer x: ID){
            int total = 0;
            for(RegularComic reg: regComics){
                if(reg.getSeries_id()!=null){
                    if(reg.getSeries_id().equals(x)){
                        System.out.println(reg.getSeries_id());
                        total = total + 1;
                    }
                }
                totalComics.add(total);
            }
        }
        List<seriesSearchPackage>result = new ArrayList<seriesSearchPackage>();
        for(int i = 0;i<titles.size();i++){
            seriesSearchPackage add = new seriesSearchPackage(titles.get(i),picPath.get(i),authors.get(i),ID.get(i),totalComics.get(i));
            result.add(add);
        }
        int count = result.size();
        // System.out.println("totalcount in series:" + count);
        if(pagenum*15>result.size()){
            System.out.print("-----------in if series---------");
            result = result.subList((pagenum-1)*15,count);
        }else{
            result = result.subList((pagenum-1)*15,pagenum*15);
        }

        JSONObject go = new JSONObject();
        go.put("TPALV",result);
        go.put("pagenumber",pagenum);
        go.put("totalpage",Math.ceil(count/15.0));
        //System.out.println(go.toString());
        return go.toString();
    }


    @RequestMapping(value="/game",method = RequestMethod.POST)
    @ResponseBody
    public String searchByKeywordgame(HttpServletRequest request){
        String keyword = request.getParameter("keyword");
        String pagenum1 = request.getParameter("page");
        System.out.println("game!!!!!!!!!!!!!!" + keyword + "\t" + pagenum1);
        int pagenum = Integer.parseInt(pagenum1);
        List<String>titles = new ArrayList<String>();
        List<String>keywords = new ArrayList<String>();
        List<String>path = new ArrayList<String>();
        List<Integer>IDs = new ArrayList<>();
        List<GameComic> gameComics = gameService.getAllGameComics();
        for (GameComic gc: gameComics){
            Comic comic = comicService.getComicByComic_Id(gc.getGame_comic_id());
            if(comic.getStatus()==1||comic.getStatus()==3){
                if(comic.getTitle().toLowerCase().contains(keyword.toLowerCase())||gc.getKeyword().toLowerCase().contains(keyword.toLowerCase())){
                    titles.add(comic.getTitle());
                    keywords.add(gc.getKeyword());
                    IDs.add(comic.getComic_id());
                    Panel panel = panelService.getPanelFromPanelId(gc.getPanel1_id());
                    path.add(panel.getCanvas_path());
                }
            }
        }
        Integer count = path.size();
        if(pagenum*15>path.size()){
            System.out.print("-----------in if game---------");
            path = path.subList((pagenum-1)*15,count);
            keywords = keywords.subList((pagenum-1)*15,count);
            IDs = IDs.subList((pagenum-1)*15,count);
            titles = titles.subList((pagenum-1)*15,count);
        }else{
            path = path.subList((pagenum-1)*15,pagenum*15);
            keywords = keywords.subList((pagenum-1)*15,pagenum*15);
            IDs = IDs.subList((pagenum-1)*15,pagenum*15);
            titles = titles.subList((pagenum-1)*15,pagenum*15);
        }
        JSONObject go = new JSONObject();
        go.put("pagenumber",pagenum);
        go.put("totalpage",Math.ceil(count/15.0));
        go.put("titles",titles);
        go.put("IDs", IDs);
        go.put("keywords",keywords);
        go.put("path",path);
        //System.out.println(go.toString());
        return go.toString();
    }
    private String getAuthorbyFolderID(int id){
        List<Folder>all = this.folderService.getAllFolders();
        for(Folder f: all){
            if(f.getFolder_id().equals(id)){
                return f.getUsername();
            }
        }
        return "user1";
    }
}
