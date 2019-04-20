package cotube.controller;

import cotube.domain.Account;
import cotube.domain.Comic;
import cotube.domain.RegularComic;
import cotube.domain.Series;
import cotube.domain.FollowSeries;
import cotube.domain.Panel;
import cotube.domain.FollowUser;
import cotube.domain.Series;
import cotube.services.ComicService;
import cotube.services.AccountService;
import cotube.services.LikesService;
import cotube.services.RegularComicService;
import cotube.services.ViewsService;
import cotube.services.FollowSeriesService;
import cotube.services.PanelService;
import cotube.services.FollowUserService;
import cotube.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import org.json.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

@Controller
@RequestMapping(value="/index.html")
public class ajaxHomeController{

    @Autowired
    private ViewsService viewsService;

    @Autowired
    private LikesService likesService;

    @Autowired
    private RegularComicService regularComicService;

    @Autowired
    private FollowSeriesService followSeriesService;

    @Autowired
    private PanelService panelService;

    @Autowired
    private FollowUserService followUserService;

    @Autowired
    private ComicService comicService;

    @Autowired
    private SeriesService seriesService;



    @RequestMapping(value="/trending",method = RequestMethod.POST)
    @ResponseBody
    public String getTrending(HttpServletRequest request){
        List<RegularComic> regularComics = new ArrayList<>();
        List<Comic> comics = viewsService.getHighestViewedRegularComics();
        List<Comic> resultComics = new ArrayList<>();
        int counter = 6;
        if (comics.size() < 6){
            counter = comics.size();
        }
        Random rand = new Random();
        for (int i = 0; i < counter; i++) {
            int index = rand.nextInt(comics.size());
            RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(comics.get(index).getComic_id());
            resultComics.add(comics.get(index));
            comics.remove(index);
            regularComics.add(rc);
        }

        System.out.println(comics.toString());
        System.out.println(regularComics.toString());
        JSONObject result = new JSONObject();
        result.put("comics", resultComics);
        result.put("regular_comics", regularComics);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/popseries",method = RequestMethod.POST)
    @ResponseBody
    public String getPopularSeries(HttpServletRequest request){
        List<Series> series = viewsService.getHighestViewedSeries();
        List<Series> seriesResult = new ArrayList<>();
        int counter = 6;
        if (series.size() < 6){
            counter = series.size();
        }
        Random rand = new Random();
        for (int i = 0; i < counter; i++) {
            int index = rand.nextInt(series.size());
            seriesResult.add(series.get(index));
            series.remove(index);
        }
        System.out.println(seriesResult.toString());
        JSONObject result = new JSONObject();
        result.put("series", seriesResult);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/toprated",method = RequestMethod.POST)
    @ResponseBody
    public String getTopRatedComics(HttpServletRequest request){
        List<RegularComic> regularComics = new ArrayList<>();
        List<Comic> ratedcomics = likesService.getMostLikedRegularComics();
        List<Comic> ratedcomicsResult = new ArrayList<>();
        int counter = 5;
        if (ratedcomics.size() < 5){
            counter = ratedcomics.size();
        }

        for (int i = 0; i < counter; i++) {
            RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(ratedcomics.get(i).getComic_id());
            regularComics.add(rc);
            ratedcomicsResult.add(ratedcomics.get(i));
        }

        System.out.println(ratedcomics.toString());
        JSONObject result = new JSONObject();
        result.put("ratedcomics", ratedcomicsResult);
        result.put("regularcomics", regularComics);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/getTimeline",method = RequestMethod.POST)
    @ResponseBody
    public String getTimeline(HttpServletRequest request){
        String username = request.getParameter("username");
        List<FollowSeries> followSeries = followSeriesService.getAllFollowSeries();
        List<FollowUser> followUser = followUserService.getAllFollowUsers();
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        List<Panel> panel = panelService.getAllPanels();
        List<Integer> followSeriesId = new ArrayList<Integer>();
        List<Integer> newFollowSeriesId = new ArrayList<Integer>();
        List<String> followSeriesAuthor = new ArrayList<String>();
        List<String> followUserList = new ArrayList<String>();
        List<Integer> followSeriesIdNeeded = new ArrayList<Integer>();


        for(FollowSeries fs: followSeries){
            if(fs.getFollower_username().equals(username)){
                followSeriesId.add(fs.getSeries_id());
            }
        }

        for(Integer i: followSeriesId){
            Integer panelId = this.regularComicService.getAllRegularComicsInSeries(i).get(0).getPanel_id();
            String seriesAuthor = this.panelService.getPanelFromPanelId(panelId).getAuthor();
            if(!followSeriesAuthor.contains(seriesAuthor)){
                followSeriesAuthor.add(seriesAuthor);
                newFollowSeriesId.add(i);
            }
        }

        for(FollowUser fu: followUser){
            if(fu.getFollower_username().equals(username)){
                followUserList.add(fu.getFollowing_username());
            }
        }

        for(int i = 0; i < followSeriesAuthor.size(); i++){
            if(!followUserList.contains(followSeriesAuthor.get(i))){
                followSeriesIdNeeded.add(newFollowSeriesId.get(i));
            }
        }

        List<Integer> timelineComicId = new ArrayList<Integer>();
        for(Integer i: followSeriesIdNeeded){
            List<RegularComic> regularComicInSeries = this.regularComicService.getAllRegularComicsInSeries(i);
            for(RegularComic rc: regularComicInSeries){
                if(this.comicService.getComicByComic_Id(rc.getRegular_comic_id()).getStatus() == 1){
                    timelineComicId.add(rc.getRegular_comic_id());
                }
            }
        }

        for(String s: followUserList){
            for(Panel p: panel){
                if(p.getAuthor().equals(s)){
                   for(RegularComic rc: regularComics){
                       if(rc.getPanel_id() == p.getPanel_id()){
                            if(this.comicService.getComicByComic_Id(rc.getRegular_comic_id()).getStatus() == 1){
                                timelineComicId.add(rc.getRegular_comic_id());    
                            }
                       }
                   }
                }
            }
        }

        Collections.sort(timelineComicId);
        Collections.reverse(timelineComicId);

        List<String> comicThumbnail = new ArrayList<String>();
        List<String> comicTitle = new ArrayList<String>();
        List<String> comicTime = new ArrayList<String>();
        List<String> comicAuthor = new ArrayList<String>();
        List<String> comicDescription = new ArrayList<String>();
        List<Boolean> comicSeries = new ArrayList<Boolean>();
        List<String> comicSeriesTitle = new ArrayList<String>();

        for(Integer i:timelineComicId){
            RegularComic rc = this.regularComicService.getRegularComicByRegular_Comic_Id(i);
            Comic c = this.comicService.getComicByComic_Id(i);
            Integer panelId = rc.getPanel_id();
            Panel p = this.panelService.getPanelFromPanelId(panelId);
            comicThumbnail.add(rc.getThumbnail_path());
            comicTitle.add(c.getTitle());
            comicTime.add(c.getDate_published().toString());
            comicAuthor.add(p.getAuthor());
            comicDescription.add(rc.getDescription());
            comicSeries.add(rc.getSeries_id()==null?false:true);
            if(rc.getSeries_id()==null?false:true){
                comicSeriesTitle.add(this.seriesService.getSeriesBySeriesId(rc.getSeries_id()).getSeries_name());
            }else{
                comicSeriesTitle.add("null");
            }
        }

        JSONObject result = new JSONObject();
        result.put("comicId", timelineComicId);
        result.put("comicThumbnail", comicThumbnail);
        result.put("comicTitle", comicTitle);
        result.put("comicTime", comicTime);
        result.put("comicAuthor", comicAuthor);
        result.put("comicDescription", comicDescription);
        result.put("comicSeries", comicSeries);
        result.put("comicSeriesTitle", comicSeriesTitle);
        System.out.println(result.toString());
        return result.toString();
    }

    

}