package cotube.controller;

import cotube.domain.Account;
import cotube.domain.Comic;
import cotube.services.AccountService;
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


    @RequestMapping(value="/trending",method = RequestMethod.POST)
    @ResponseBody
    public String getTrending(HttpServletRequest request){

        List<Comic> comics = viewsService.getHighestViewedRegularComics();
        while (comics.size() > 20)
            comics.remove(20);

        JSONObject result = new JSONObject();
        result.put("comics", comics);
        System.out.println(result.toString());
        return result.toString();
    }


}