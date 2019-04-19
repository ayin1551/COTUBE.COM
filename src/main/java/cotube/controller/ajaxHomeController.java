package cotube.controller;

import cotube.domain.Account;
import cotube.domain.Comic;
import cotube.domain.RegularComic;
import cotube.domain.Series;
import cotube.services.AccountService;
import cotube.services.LikesService;
import cotube.services.RegularComicService;
import cotube.services.ViewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import org.json.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/index.html")
public class ajaxHomeController{

    @Autowired
    private ViewsService viewsService;

    @Autowired
    private LikesService likesService;

    @Autowired
    private RegularComicService regularComicService;


    @RequestMapping(value="/trending",method = RequestMethod.POST)
    @ResponseBody
    public String getTrending(HttpServletRequest request){
        List<RegularComic> regularComics = new ArrayList<>();
        List<Comic> comics = viewsService.getHighestViewedRegularComics();
        List<Comic> comics2 = viewsService.getHighestViewedRegularComics();
        List<Comic> comics3 = viewsService.getHighestViewedRegularComics();

        for (int i = 0; i < comics.size(); i++) {
            RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(comics.get(i).getComic_id());
            regularComics.add(rc);
        }

        for (int i = 0; i < comics2.size(); i++) {
            RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(comics.get(i).getComic_id());
            regularComics.add(rc);
        }

        for (int i = 0; i < comics3.size(); i++) {
            RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(comics.get(i).getComic_id());
            regularComics.add(rc);
        }
        comics.addAll(comics2);
        comics.addAll(comics3);
        System.out.println(comics.toString());
        System.out.println(regularComics.toString());
        JSONObject result = new JSONObject();
        result.put("comics", comics);
        result.put("regular_comics", regularComics);
        System.out.println(result.toString());
        return result.toString();
    }

    @RequestMapping(value="/popseries",method = RequestMethod.POST)
    @ResponseBody
    public String getPopularSeries(HttpServletRequest request){
            List<Series> series = viewsService.getHighestViewedSeries();
            List<Series> series2 = viewsService.getHighestViewedSeries();
            List<Series> series3 = viewsService.getHighestViewedSeries();

            series.addAll(series2);
            series.addAll(series3);
            System.out.println(series.toString());
            JSONObject result = new JSONObject();
            result.put("series", series);
            System.out.println(result.toString());
            return result.toString();
    }

    @RequestMapping(value="/toprated",method = RequestMethod.POST)
    @ResponseBody
    public String getTopRatedComics(HttpServletRequest request){
        List<RegularComic> regularComics = new ArrayList<>();
        List<Comic> ratedcomics = likesService.getMostLikedRegularComics();
        List<Comic> comics2= likesService.getMostLikedRegularComics();
        List<Comic> comics3 = likesService.getMostLikedRegularComics();


        for (int i = 0; i < ratedcomics.size(); i++) {
            RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(ratedcomics.get(i).getComic_id());
            regularComics.add(rc);
        }

        for (int i = 0; i < comics2.size(); i++) {
            RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(comics2.get(i).getComic_id());
            regularComics.add(rc);
        }

        for (int i = 0; i < comics3.size(); i++) {
            RegularComic rc = regularComicService.getRegularComicByRegular_Comic_Id(comics3.get(i).getComic_id());
            regularComics.add(rc);
        }
        ratedcomics.addAll(comics2);
        ratedcomics.addAll(comics3);
        regularComics.remove(5);
        ratedcomics.remove(5);
        System.out.println(ratedcomics.toString());
        JSONObject result = new JSONObject();
        result.put("ratedcomics", ratedcomics);
        result.put("regularcomics", regularComics);
        System.out.println(result.toString());
        return result.toString();
    }
}