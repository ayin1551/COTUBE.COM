package cotube.controller;
import cotube.domain.*;
import cotube.services.AccountService;
import cotube.services.ComicService;
import cotube.services.PanelService;
import cotube.services.RegularComicService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/viewComicsByTitle.html")
public class ajaxViewComicsByTitle {
    private RegularComicService regularComicService;

    @Autowired
    private void setRegularComicService(RegularComicService regularComicService) {
        this.regularComicService = regularComicService;
    }

    private PanelService panelService;

    @Autowired
    public void setPanelService(PanelService panelService) {
        this.panelService = panelService;
    }

    private ComicService comicService;

    @Autowired
    public void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }

    @RequestMapping(value = "/comicTitles", method = RequestMethod.POST)
    @ResponseBody
    public String searchByAuthor() {
        List<Comic> comics = this.comicService.getAllComics();
        List<RegularComic> regularComics = this.regularComicService.getAllRegularComics();
        List<String> titles = new ArrayList<String>();
        List<String> authors = new ArrayList<String>();
        List<Integer> ids = new ArrayList<Integer>();
        List<Comic>Filter = new ArrayList<>();
        for (Comic c: comics){
            if(c.getStatus() == 1 || c.getStatus() == 3){
                if(c.getComic_type() == 0){
                    Filter.add(c);
                }
            }
        }
        for (Comic c : Filter) {
            titles.add(c.getTitle());
            ids.add(c.getComic_id());
            for (RegularComic reg : regularComics) {
                if (reg.getRegular_comic_id() == c.getComic_id()) {
                    authors.add(getAuthor(reg));
                }
            }
        }
        List<titleAndAuthor>result = new ArrayList<>();
        System.out.println(titles.size());
        System.out.println(authors.size());
        System.out.println(ids.size());
        for (int i = 0;i < titles.size(); i++){
            titleAndAuthor packed = new titleAndAuthor(titles.get(i),authors.get(i),ids.get(i));
            result.add(packed);
        }
        JSONObject go = new JSONObject();
        go.put("TNA",result);
        //System.out.println(go.toString());
        return go.toString();
    }
    private String getAuthor(RegularComic reg){
        List<Panel>panels = this.panelService.getAllPanels();
        for (Panel p: panels){
            if (reg.getRegular_comic_id() == p.getPanel_id()){
                return p.getAuthor();
            }
        }
        return "NO AUTHOR";
    }
}


