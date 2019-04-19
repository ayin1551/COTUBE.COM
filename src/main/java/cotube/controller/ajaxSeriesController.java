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

    private TagService tagService;
    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    private CommentsService commentsService;
    @Autowired
    public void setCommentsService(CommentsService commentsService) {
        this.commentsService = commentsService;
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
        List<RegularComic> regularComicList = regularComicService.getAllRegularComicsInSeries(seriesId);

        for(int i =0; i < regularComicList.size(); i++){
            Integer comicId = regularComicList.get(i).getRegular_comic_id();
            Comic comic = comicService.getComicByComic_Id(comicId);

            RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(comicId);
            Integer series_id = rc.getSeries_id();

            //delete From Tag
            List<Tag> tagList = tagService.getAllTagsInRegularComic(comicId);
            for (int j = 0; j < tagList.size(); j++)
                tagService.deleteTag(tagList.get(j));

            //delete From Views
            List<Views> viewsList = viewsService.getAllViewsInComic(comicId);
            for (int j = 0; j < viewsList.size(); j++)
                viewsService.deleteView(viewsList.get(j));

            //delete from Likes
            List<Likes> likesList = likesService.getAllLikesInComic(comicId);
            for (int j = 0; j < likesList.size(); j++)
                likesService.deleteLike(likesList.get(j));

            //delete from Comments
            List<Comments> commentsList = commentsService.getAllCommentsInComic(comicId);
            for (int j = 0; j < commentsList.size(); j++)
                commentsService.deleteComment(commentsList.get(j));

            //delete from Favorites
            List<Favorite> favoritesList = favoriteService.getAllFavoritesInComic(comicId);
            for (int j = 0; j < favoritesList.size(); j++)
                favoriteService.deleteFavorite(favoritesList.get(j));

            //delete from RegularComic
            regularComicService.deleteRegularComic(rc);

            //delete From Panel
            panelService.deletePanel(panelService.getPanelFromPanelId(rc.getPanel_id()));

            //delete from Comic
            comicService.deleteComic(comic);

        }

        //delete from FollowSeries
        List<FollowSeries> followSeriesList = followSeriesService.getAllFollowSeriesInSeries(seriesId);
        for (int j = 0; j < followSeriesList.size(); j++)
            followSeriesService.deleteFollowSeries(followSeriesList.get(j));

        //delete from Series
        seriesService.deleteSeries(seriesService.getSeriesBySeriesId(seriesId));


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

                //delete From Tag
                List<Tag> tagList = tagService.getAllTagsInRegularComic(comicId);
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
}
