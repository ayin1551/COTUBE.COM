package cotube.controller;

import cotube.services.*;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.security.Key;
import java.util.*;

import cotube.domain.Likes;
import cotube.domain.*;
import cotube.domain.Comments;
import cotube.domain.GameComic;
import cotube.domain.Views;
import java.lang.Math;

@Controller
@RequestMapping(value="/viewGameComicsByKeyword.html")
public class ajaxGameComicsLookup{
    private GameComicService gameComicService;
    @Autowired
    public void setGameComicService(GameComicService gameComicService) {
        this.gameComicService = gameComicService;
    }
    private ComicService ComicService;
    @Autowired
    public void setComicService(ComicService ComicService) {
        this.ComicService = ComicService;
    }

    private PanelService panelService;
    @Autowired
    public void setPanelService (PanelService panelService){
        this.panelService = panelService;
    }


    private KeywordService keywordService;
    @Autowired
    public void setKeywordService (KeywordService keywordService){
        this.keywordService = keywordService;
    }

    private ArrayList<Keyword> getFive(List<Keyword> all){
        Random random = new Random();
        ArrayList<Keyword> result = new ArrayList<>();
        ArrayList<Integer> randomIndex = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(all.size());
            result.add(all.get(index));
            all.remove(index);
        }

        return result;
    }
    private boolean isValid(GameComic x){
        int game_id = x.getGame_comic_id();
        Comic check = this.ComicService.getComicByComic_Id(game_id);
        if(check.getStatus() == 1){
            return true;
        }
        if(check.getStatus() == 3){
            return true;
        }
        return false;
    }
    @RequestMapping(value="/getGameComics",method = RequestMethod.POST)
    @ResponseBody
    public String getGameComics(HttpServletRequest request){
        List<Keyword> allKW = this.keywordService.getAllKeywords();
        List<Keyword> fiveKW = getFive(allKW);
       // List<Keyword> fiveKW =this.keywordService.getAllKeywords();
        List<GameComic> all = this.gameComicService.getAllGameComics();
        List<imgsrcANDtitle>details1 = new ArrayList<>();
        List<imgsrcANDtitle>details2 = new ArrayList<>();
        List<imgsrcANDtitle>details3 = new ArrayList<>();
        List<imgsrcANDtitle>details4 = new ArrayList<>();
        List<imgsrcANDtitle>details5 = new ArrayList<>();
        for (int i = 0; i < 5 ;i++){
            String keyword = fiveKW.get(i).getKeyword();
            System.out.println(keyword);
            for (GameComic x: all){
                if (x.getKeyword().equals(keyword) && isValid(x)){
                    imgsrcANDtitle z = new imgsrcANDtitle();
                    z.comic_id = x.getGame_comic_id();
                    if (x.getPanel1_id() != null){
                        Panel p = this.panelService.getPanelFromPanelId(x.getPanel1_id());
                        z.panel1Src = p.getCanvas_path();
                        z.panel1Title = p.getTitle_word();
                    }
                    if (x.getPanel2_id() != null){
                        Panel p = this.panelService.getPanelFromPanelId(x.getPanel2_id());
                        z.panel2Title = p.getTitle_word();
                    }
                    if (x.getPanel3_id() != null){
                        Panel p = this.panelService.getPanelFromPanelId(x.getPanel3_id());
                        z.panel3Title = p.getTitle_word();
                        System.out.println(z.panel3Title);
                    }
                    if (x.getPanel4_id() != null){
                        Panel p = this.panelService.getPanelFromPanelId(x.getPanel4_id());
                        z.panel4Title = p.getTitle_word();
                    }
                    switch (i){
                        case 0: details1.add(z);
                        break;
                        case 1: details2.add(z);
                        break;
                        case 2: details3.add(z);
                        break;
                        case 3: details4.add(z);
                        break;
                        case 4: details5.add(z);
                        break;
                    }
                }
            }
        }
        JSONObject result = new JSONObject();
        result.put("D1",details1);
        result.put("D2",details2);
        result.put("D3",details3);
        result.put("D4",details4);
        result.put("D5",details5);
        result.put("KW",fiveKW);
        return result.toString();
    }
}
