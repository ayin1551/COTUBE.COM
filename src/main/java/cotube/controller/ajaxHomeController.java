package cotube.controller;

import cotube.domain.Comic;
import cotube.domain.RegularComic;
import cotube.domain.Series;
import cotube.domain.FollowSeries;
import cotube.domain.Panel;
import cotube.domain.FollowUser;
import cotube.domain.GameComic;
import cotube.services.ComicService;
import cotube.services.LikesService;
import cotube.services.RegularComicService;
import cotube.services.ViewsService;
import cotube.services.FollowSeriesService;
import cotube.services.PanelService;
import cotube.services.FollowUserService;
import cotube.services.GameComicService;
import cotube.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import org.json.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
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

    @Autowired
    private GameComicService gameComicService;



    @RequestMapping(value="/trending",method = RequestMethod.POST)
    @ResponseBody
    public String getTrending(HttpServletRequest request){
        List<RegularComic> regularComics = new ArrayList<>();
        List<Comic> comics = viewsService.getHighestViewedRegularComics();
        System.out.println("");
        System.out.println(comics);
        List<Comic> resultComics = new ArrayList<>();
        int counter = 6;
        if (comics.size() < 6){
            counter = comics.size();
        }
        Random rand = new Random();
        for (int i = 0; i < counter; i++) {
            int index = rand.nextInt(comics.size());
            System.out.println("INDEX:" + index);
            System.out.println("Comic ID: " + comics.get(index).getComic_id());
            RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(comics.get(index).getComic_id());
            resultComics.add(comics.get(index));
            comics.remove(index);
            regularComics.add(rc);
        }

        JSONObject result = new JSONObject();
        result.put("comics", resultComics);
        result.put("regular_comics", regularComics);
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
        JSONObject result = new JSONObject();
        result.put("series", seriesResult);

        String test = request.getRequestURI();

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

        JSONObject result = new JSONObject();
        result.put("ratedcomics", ratedcomicsResult);
        result.put("regularcomics", regularComics);
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
                if(this.comicService.getComicByComic_Id(rc.getRegular_comic_id()).getStatus() == 1 || this.comicService.getComicByComic_Id(rc.getRegular_comic_id()).getStatus() == 3){
                    timelineComicId.add(rc.getRegular_comic_id());
                }
            }
        }

        for(String s: followUserList){
            for(Panel p: panel){
                if(p.getAuthor().equals(s)){
                    Boolean findFlag = false;
                    for(RegularComic rc: regularComics){
                        if(rc.getPanel_id().equals(p.getPanel_id())){
                            if(this.comicService.getComicByComic_Id(rc.getRegular_comic_id()).getStatus() == 1 || this.comicService.getComicByComic_Id(rc.getRegular_comic_id()).getStatus() == 3){
                                timelineComicId.add(rc.getRegular_comic_id());    
                                findFlag = true;
                                break;
                            }
                        }
                    }

                    if(!findFlag){
                        if(p.getTitle_word() != null){
                            Integer panelId = p.getPanel_id();
                            Integer gameComicId = this.gameComicService.getGameComicIdByPanelId(panelId);
                        
                            GameComic gc = this.gameComicService.getGameComicByGameComicId(gameComicId);
                            if(this.comicService.getComicByComic_Id(gameComicId).getStatus() == 1 || this.comicService.getComicByComic_Id(gameComicId).getStatus() == 3){
                                if(!timelineComicId.contains(gc.getGame_comic_id())){
                                    timelineComicId.add(gc.getGame_comic_id());
                                }
                            }
                            
                        }
                    }

                }
            }
        }
        List<Comic> comics = new ArrayList<Comic>();
        for(Integer i: timelineComicId){
            comics.add(comicService.getComicByComic_Id(i));
        }
        Collections.sort(comics, new Comparator<Comic>(){ 
            public int compare(Comic o1, Comic o2) {
                return o2.getDate_published().compareTo(o1.getDate_published());
            }
        });
        
        timelineComicId = new ArrayList<Integer>();
        for(Comic c: comics){
            timelineComicId.add(c.getComic_id());
        }
        //System.out.println(timelineComicId);

        List<String> comicThumbnail = new ArrayList<String>();
        List<String> comicTitle = new ArrayList<String>();
        List<String> comicTime = new ArrayList<String>();
        List<String> comicAuthor = new ArrayList<String>();
        List<String> comicDescription = new ArrayList<String>();
        List<Boolean> comicSeries = new ArrayList<Boolean>();
        List<String> comicSeriesTitle = new ArrayList<String>();
        List<Integer> comicSeriesId = new ArrayList<Integer>();
        List<Boolean> comicGame = new ArrayList<Boolean>();

        for(Integer i:timelineComicId){
            Comic c = this.comicService.getComicByComic_Id(i);
            if(c.getComic_type() == 0){
                RegularComic rc = this.regularComicService.getRegularComicByRegular_Comic_Id(i);
                Integer panelId = rc.getPanel_id();
                Panel p = this.panelService.getPanelFromPanelId(panelId);
                comicThumbnail.add(rc.getThumbnail_path());
                comicTitle.add(c.getTitle());
                comicTime.add(c.getDate_published().toString());
                comicAuthor.add(p.getAuthor());
                comicDescription.add(rc.getDescription());
                comicSeries.add(rc.getSeries_id()==null?false:true);
                comicGame.add(false);
                if(rc.getSeries_id()==null?false:true){
                    comicSeriesTitle.add(this.seriesService.getSeriesBySeriesId(rc.getSeries_id()).getSeries_name());
                    comicSeriesId.add(rc.getSeries_id());
                }else{
                    comicSeriesTitle.add("null");
                    comicSeriesId.add(0);
                }
            }
            else if(c.getComic_type() == 1){
                //EDIT GAME COMIC INFO TO BE SHOWN ON TIMELINE HERE

                GameComic gc = this.gameComicService.getGameComicByGameComicId(i);
                Integer panel1Id = gc.getPanel1_id();
                Integer panel2Id = gc.getPanel2_id();
                Integer panel3Id = gc.getPanel3_id();
                Integer panel4Id = gc.getPanel4_id();
                Panel p1 = this.panelService.getPanelFromPanelId(panel1Id);
                Panel p2 = this.panelService.getPanelFromPanelId(panel2Id);
                Panel p3 = this.panelService.getPanelFromPanelId(panel3Id);
                Panel p4 = this.panelService.getPanelFromPanelId(panel4Id);
                comicTitle.add(c.getTitle());
                comicTime.add(c.getDate_published().toString());
                comicThumbnail.add(p1.getCanvas_path());
                comicAuthor.add(p1.getAuthor() + "," + p2.getAuthor() + "," + p3.getAuthor() + "," + p4.getAuthor());
                comicDescription.add(gc.getKeyword());
                comicSeries.add(false);
                comicSeriesTitle.add(null);
                comicSeriesId.add(null);
                comicGame.add(true);
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
        result.put("comicSeriesId", comicSeriesId);
        result.put("comicGame", comicGame);
        return result.toString();
    }

    

}