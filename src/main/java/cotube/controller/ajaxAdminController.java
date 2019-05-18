package cotube.controller;
import cotube.domain.*;
import cotube.services.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="/admin.html")
public class ajaxAdminController {
    private ComicService comicService;
    @Autowired
    public void setComicService(ComicService comicService){
        this.comicService = comicService;
    }
    private CommentsService commentService;
    @Autowired
    public void setCommentService(CommentsService commentService){
        this.commentService = commentService;
    }
    private PanelService panelService;
    @Autowired
    public void setPanelService(PanelService panelService) {
        this.panelService = panelService;
    }
    private RegularComicService regularComicService;
    @Autowired
    private void setRegularComicService(RegularComicService regularComicService){
        this.regularComicService = regularComicService;
    }
    private NotificationService notificationService;
    @Autowired
    private void setNotificationService(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @RequestMapping(value="/getComics",method = RequestMethod.POST)
    @ResponseBody
    public String getComicsToCensor(HttpServletRequest request){
        List<Comic> all = this.comicService.getAllComics();
        List<String>titles = new ArrayList<>();
        List<String>authors = new ArrayList<>();
        List<Date> dates = new ArrayList<>();
        List<Integer>IDS = new ArrayList<>();
        List<comicCensorPackage> result = new ArrayList<>();
        List<RegularComic>regularComics = this.regularComicService.getAllRegularComics();
        for (Comic c: all){
            if (c.getStatus() == 3) {
                titles.add(c.getTitle());
                dates.add(c.getDate_published());
                IDS.add(c.getComic_id());
                for (RegularComic reg: regularComics){
                    if (reg.getRegular_comic_id() == c.getComic_id()){
                        authors.add(panelService.getPanelFromPanelId(reg.getPanel_id()).getAuthor());
                    }
                }
            }
        }
        for (int i = 0;i<titles.size();i++){
            comicCensorPackage add = new comicCensorPackage(IDS.get(i),titles.get(i),dates.get(i));
            result.add(add);
        }
        JSONObject go = new JSONObject();
        go.put("COMICS",result);
        go.put("author",authors);
        return go.toString();
    }
    @RequestMapping(value="/passComic",method = RequestMethod.POST)
    @ResponseBody
    public boolean passComic(HttpServletRequest request) {
        String title = request.getParameter("comicTitle");
        Comic change = this.comicService.getComicByComic_Id(Integer.parseInt(title));
        change.setStatus(1);
        this.comicService.addComic(change);

        Date now = new Date();
        int notification_type = 3;
        String notification = "An admin has passed your comic: " + change.getTitle();

        List<RegularComic>regularComics = this.regularComicService.getAllRegularComics();
        String username = "";
        for (RegularComic reg: regularComics){
            if (reg.getRegular_comic_id() == change.getComic_id()){
                username = getAuthor(reg);
                System.out.println(username);
            }
        }

        Notification note = new Notification();
        note.setNotifcation_type(notification_type);
        note.setNotification(notification);
        note.setUsername(username);
        note.setNotifcation_time(now);
        note.setLink(Integer.toString(change.getComic_id()));
        this.notificationService.addNotification(note);
        return true;
    }

    @RequestMapping(value="/denyComic",method = RequestMethod.POST)
    @ResponseBody
    public boolean denyComic(HttpServletRequest request) {
        String title = request.getParameter("comicTitle");
        System.out.println(title);
        Comic change = this.comicService.getComicByComic_Id(Integer.parseInt(title));
        change.setStatus(2);
        this.comicService.addComic(change);

        Date now = new Date();
        int notification_type = 1;
        String notification = "An admin has denied your comic: " + change.getTitle();

        List<RegularComic>regularComics = this.regularComicService.getAllRegularComics();
        String username = "";
        for (RegularComic reg: regularComics){
            if (reg.getRegular_comic_id() == change.getComic_id()){
                username = getAuthor(reg);
                System.out.println(username);
            }
        }

        Notification note = new Notification();
        note.setNotifcation_type(notification_type);
        note.setNotification(notification);
        note.setUsername(username);
        note.setNotifcation_time(now);
        this.notificationService.addNotification(note);
        return true;
    }

    @RequestMapping(value="/getComments",method = RequestMethod.POST)
    @ResponseBody
    public String getComments(HttpServletRequest request) {
        List<Comments> comments = this.commentService.getAllComments();
        List<Comments> result = new ArrayList<>();
        for(Comments c: comments){
            if (c.getStatus() == 2){
                result.add(c);
            }
        }
        JSONObject go = new JSONObject();
        go.put("COMMENTS",result);
        return go.toString();
    }

    @RequestMapping(value="/passComment",method = RequestMethod.POST)
    @ResponseBody
    public boolean passComment(HttpServletRequest request) {
        String comicId = request.getParameter("comicID");
        String commentNum = request.getParameter("comicNum");
        List<Comments> comments = this.commentService.getAllComments();
        for(Comments c: comments){
            if (c.getComic_id() == Integer.parseInt(comicId)){
                if(c.getComment_number() == Integer.parseInt(commentNum)){
                    c.setStatus(0);
                    this.commentService.addComments(c);
                }
            }
        }

        return true;
    }

    @RequestMapping(value="/denyComment",method = RequestMethod.POST)
    @ResponseBody
    public boolean denyComment(HttpServletRequest request) {
        String comicId = request.getParameter("comicID");
        String commentNum = request.getParameter("comicNum");
        List<Comments> comments = this.commentService.getAllComments();
        String target = "";
        for(Comments c: comments){
            if (c.getComic_id() == Integer.parseInt(comicId)){
                if(c.getComment_number() == Integer.parseInt(commentNum)){
                    c.setStatus(1);
                    target = c.getCommenter_username();
                    this.commentService.addComments(c);
                }
            }
        }
        Date now = new Date();
        int notification_type = 2;
        String notification = "An admin has denied your comment in " + this.comicService.getComicByComic_Id(Integer.parseInt(comicId)).getTitle();
        Notification note = new Notification();
        note.setNotifcation_type(notification_type);
        note.setNotification(notification);
        note.setUsername(target);
        note.setNotifcation_time(now);
        note.setLink(comicId);
        this.notificationService.addNotification(note);
        return true;
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
