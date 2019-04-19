package cotube.controller;

import cotube.domain.FollowUser;
import cotube.domain.Account;
import cotube.domain.Folder;
import cotube.domain.FollowSeries;
import cotube.domain.Series;
import cotube.domain.Comic;
import cotube.domain.RegularComic;
import cotube.domain.Panel;
import cotube.domain.Views;
import cotube.domain.Likes;

import cotube.services.FollowUserService;
import cotube.services.AccountService;
import cotube.services.FolderService;
import cotube.services.FollowSeriesService;
import cotube.services.SeriesService;
import cotube.services.ComicService;
import cotube.services.RegularComicService;
import cotube.services.PanelService;
import cotube.services.ViewsService;
import cotube.services.LikesService;

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
import java.sql.Timestamp;

@Controller
@RequestMapping(value="/viewSeries.html")
public class ajaxSeriesController{

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

    private ComicService comicService;
    @Autowired
    public void setComicService(ComicService comicService) {
        this.comicService = comicService;
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

    @RequestMapping(value="/follow",method = RequestMethod.POST)
    @ResponseBody
    public Boolean follow(HttpServletRequest request){
        String username = request.getParameter("username");
        Integer id = Integer.parseInt(request.getParameter("seriesId"));
 
        FollowSeries fs = new FollowSeries();
        Date date = new Date();
        fs.setFollow_time(new Timestamp(date.getTime()));
        fs.setFollower_username(username);
        fs.setSeries_id(id);
        this.followSeriesService.addFollowSeries(fs);

        return true;
    }

    @RequestMapping(value="/unfollow",method = RequestMethod.POST)
    @ResponseBody
    public Boolean unfollow(HttpServletRequest request){
        String username = request.getParameter("username");
        Integer id = Integer.parseInt(request.getParameter("seriesId"));
        List<FollowSeries> followSeries = followSeriesService.getAllFollowSeries();
        for(FollowSeries fs: followSeries){
            if(fs.getFollower_username().equals(username) && fs.getSeries_id() == id){
                this.followSeriesService.deleteFollowSeries(fs);
                return true;
            }
        }
        return false;
    }


    // Check if the user has already followed this series
    @RequestMapping(value="/check",method = RequestMethod.POST)
    @ResponseBody
    public Boolean check(HttpServletRequest request){
        String username = request.getParameter("username");
        Integer id = Integer.parseInt(request.getParameter("seriesId"));
        
        List<FollowSeries> followSeries = followSeriesService.getAllFollowSeries();
        for(FollowSeries fs: followSeries){
            if(fs.getFollower_username().equals(username) && fs.getSeries_id() == id){
                return true;
            }
        }

        return false;
    }

    @RequestMapping(value="/getInfo",method = RequestMethod.POST)
    @ResponseBody
    public String getInfo(HttpServletRequest request){
        Integer seriesId = Integer.parseInt(request.getParameter("seriesId"));
        List<Series> series = seriesService.getAllSeries();
        List<FollowSeries> followSeries = followSeriesService.getAllFollowSeries();
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        List<Comic> comics = comicService.getAllComics();
        List<Likes> likes = likesService.getAllLikes();
        List<Views> views = viewsService.getAllViews();
        List<Panel> panel = panelService.getAllPanels();
        List<Integer> comicId = new ArrayList<Integer>();
        List<String> comicThumbnail = new ArrayList<String>();
        List<String> comicName = new ArrayList<String>();


        String seriesName = "";
        String seriesThumbnail = "";
        String seriesAuthor = "";
        int following = 0;
        int viewCount = 0;
        int likeCount = 0;

        for(Series s: series){
            if(s.getSeries_id() == seriesId){
                seriesName = s.getSeries_name();
                seriesThumbnail = s.getSeries_thumbnail_path();
            }
        }

        for(FollowSeries fs: followSeries){
            if(fs.getSeries_id() == seriesId){
                following += 1;
            }
        }

        for(RegularComic rc: regularComics){
            if(rc.getSeries_id() == seriesId){
                comicId.add(rc.getRegular_comic_id());
                if(seriesAuthor.equals("")){
                    for(Panel p: panel){
                        if(p.getPanel_id() == rc.getPanel_id()){
                            seriesAuthor = p.getAuthor();
                            break;
                        }
                    }
                }
            }
        }
        Collections.sort(comicId);
        Collections.reverse(comicId);

        for(Integer i: comicId){
            for(Views v: views){
                if(v.getComic_id() == i){
                    viewCount += 1;
                }
            }
        }

        for(Integer i: comicId){
            for(Likes l: likes){
                if(l.getComic_id() == i){
                    likeCount += 1;
                }
            }
        }

        for(Integer i: comicId){
            for(RegularComic rc: regularComics){
                if(rc.getRegular_comic_id() == i){
                    comicThumbnail.add(rc.getThumbnail_path());
                    break;
                }
            }

            for(Comic c: comics){
                if(c.getComic_id() == i){
                    comicName.add(c.getTitle());
                    break;
                }
            }
        }


        JSONObject result = new JSONObject();
        result.put("comicName", comicName);
        result.put("comicId", comicId);
        result.put("comicThumbnail", comicThumbnail);
        result.put("seriesName", seriesName);
        result.put("seriesThumbnail", seriesThumbnail);
        result.put("seriesAuthor", seriesAuthor);
        result.put("following", following);
        result.put("viewCount", viewCount);
        result.put("likeCount", likeCount);
        System.out.println(result.toString());
        return result.toString();
    }


    @RequestMapping(value="/getComics",method = RequestMethod.POST)
    @ResponseBody
    public String getComics(HttpServletRequest request){
        Integer seriesId = Integer.parseInt(request.getParameter("seriesId"));
        List<Series> series = seriesService.getAllSeries();
        List<RegularComic> regularComics = regularComicService.getAllRegularComics();
        List<Comic> comics = comicService.getAllComics();
        List<Panel> panel = panelService.getAllPanels();
        List<Integer> comicId = new ArrayList<Integer>();
        List<String> comicThumbnail = new ArrayList<String>();
        List<String> comicName = new ArrayList<String>();

        for(RegularComic rc: regularComics){
            if(rc.getSeries_id() == seriesId){
                comicId.add(rc.getRegular_comic_id());
            }
        }

        Collections.sort(comicId);
        Collections.reverse(comicId);

        for(Integer i: comicId){
            for(RegularComic rc: regularComics){
                if(rc.getRegular_comic_id() == i){
                    comicThumbnail.add(rc.getThumbnail_path());
                    break;
                }
            }

            for(Comic c: comics){
                if(c.getComic_id() == i){
                    comicName.add(c.getTitle());
                    break;
                }
            }
        }

        JSONObject result = new JSONObject();
        result.put("comicName", comicName);
        result.put("comicId", comicId);
        result.put("comicThumbnail", comicThumbnail);
        System.out.println(result.toString());
        return result.toString();
    }

    //TODO:deleteSeries
    @RequestMapping(value="/deleteSeries",method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteSeries(HttpServletRequest request){
        Integer seriesId = Integer.parseInt(request.getParameter("seriesId"));
        System.out.println(seriesId);
        List<RegularComic> seriesComics = regularComicService.getAllRegularComicsInSeries(seriesId);
        for (int i = 0; i< seriesComics.size(); i++){
            Comic comic = comicService.getComicByComic_Id(seriesComics.get(i).getRegular_comic_id());
            regularComicService.deleteRegularComic(seriesComics.get(i));
            comicService.deleteComic(comic);

        }
    

        return false;
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
            regularComicService.deleteRegularComic(rc);
            comicService.deleteComic(comic);
            if (series_id != null) {
                List<RegularComic> rcSeriesList = regularComicService.getAllRegularComicsInSeries(series_id);
                if(rcSeriesList.isEmpty()){
                    seriesService.deleteSeries(seriesService.getSeriesBySeriesId(series_id));
                }
            }
            return true;
        }

        return false;
    }
}
