package cotube.controller;

import cotube.domain.Account;
import cotube.domain.Comic;
import cotube.domain.RegularComic;
import cotube.services.AccountService;
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


}