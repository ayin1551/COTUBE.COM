package cotube.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import java.util.Date;
import cotube.domain.Likes;
import cotube.services.LikesService;
import cotube.domain.*;
import cotube.services.AccountService;
import cotube.services.ComicService;
import cotube.domain.Comments;
import cotube.services.CommentsService;
import cotube.services.PanelService;
import cotube.services.GameComicService;
import cotube.domain.GameComic;
import cotube.domain.Views;
import cotube.services.ViewsService;
import java.util.ArrayList;
import java.util.Collections;

@Controller
@RequestMapping(value="/viewGameComicsByKeyword.html")
public class ajaxGameComicsLookup{
    private GameComicService gameComicService;
    @Autowired
    public void setGameComicService(GameComicService gameComicService) {
        this.gameComicService = gameComicService;
    }

    private PanelService panelService;
    @Autowired
    public void setPanelService (PanelService panelService){
        this.panelService = panelService;
    }

    @RequestMapping(value="/getGameComics",method = RequestMethod.POST)
    @ResponseBody
    public String getGameComics(HttpServletRequest request){
        List<GameComic> all = this.gameComicService.getAllGameComics();
        List allPanels = this.panelService.getAllPanels();
        List<imgsrcANDtitle>details = new ArrayList<>();
        for (GameComic x: all){
            imgsrcANDtitle z = new imgsrcANDtitle();
            z.comic_id = x.getGame_comic_id();
            if (x.getPanel1_id() != null){
                Panel p = this.panelService.getPanelFromPanelId(x.getPanel1_id());
                z.panel1Src = p.getCanvas_path();
                z.panel1Title = p.getTitle_word();
            }
            if (x.getPanel2_id() != null){
                Panel p = this.panelService.getPanelFromPanelId(x.getPanel2_id());
                z.panel2Src = p.getCanvas_path();
                z.panel2Title = p.getTitle_word();
            }
            if (x.getPanel3_id() != null){
                Panel p = this.panelService.getPanelFromPanelId(x.getPanel3_id());
                z.panel3Src = p.getCanvas_path();
                z.panel3Title = p.getTitle_word();
                System.out.println(z.panel3Title);
            }
            if (x.getPanel4_id() != null){
                Panel p = this.panelService.getPanelFromPanelId(x.getPanel4_id());
                z.panel4Src = p.getCanvas_path();
                z.panel4Title = p.getTitle_word();
            }
            details.add(z);
        }
        for (imgsrcANDtitle x: details){
            System.out.println(x.panel2Title);
        }
        JSONObject result = new JSONObject();
        result.put("Details",details);
        result.put("COMICS",all);
        return result.toString();
    }
}
