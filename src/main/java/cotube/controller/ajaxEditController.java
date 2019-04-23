package cotube.controller;

import cotube.domain.RegularComic;
import cotube.domain.Panel;
import cotube.services.PanelService;
import cotube.services.RegularComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value = "/editComic.html")
public class ajaxEditController {

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

    @RequestMapping(value="/getComic",method = RequestMethod.POST)
    @ResponseBody
    public String getComic(HttpServletRequest request){
        Integer comicId = Integer.parseInt(request.getParameter("comicId"));
        RegularComic rc = this.regularComicService.getRegularComicByRegular_Comic_Id(comicId);
        Integer panelId = rc.getPanel_id();
        Panel p = this.panelService.getPanelFromPanelId(panelId);

        return p.getCanvas_path();
    }
}
