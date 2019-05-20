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
    private GameComicService gameComicService;
    @Autowired
    private void setGameComicService(GameComicService gameComicService){
        this.gameComicService = gameComicService;
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
        List<Integer>ctypes = new ArrayList<>();
        List<comicCensorPackage> result = new ArrayList<>();
        for (Comic c: all){
            if (c.getStatus() == 3) {
                titles.add(c.getTitle());
                dates.add(c.getDate_published());
                IDS.add(c.getComic_id());
                ctypes.add(c.getComic_type());
                if (c.getComic_type() == 0) {
                    authors.add(panelService.getPanelFromPanelId(regularComicService.getRegularComicByRegular_Comic_Id(c.getComic_id()).getPanel_id()).getAuthor());
                }
                else {
                    GameComic gc = gameComicService.getGameComicByGameComicId(c.getComic_id());
                    authors.add(panelService.getPanelFromPanelId(gc.getPanel1_id()).getAuthor() + " " +
                            panelService.getPanelFromPanelId(gc.getPanel2_id()).getAuthor() + " " +
                            panelService.getPanelFromPanelId(gc.getPanel3_id()).getAuthor() + " " +
                            panelService.getPanelFromPanelId(gc.getPanel4_id()).getAuthor() + " ");
                }
            }
        }
        for (int i = 0;i<titles.size();i++){
            comicCensorPackage add = new comicCensorPackage(IDS.get(i),titles.get(i),dates.get(i), ctypes.get(i));
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

        if (change.getComic_type() == 0) {
            String username = panelService.getPanelFromPanelId(regularComicService.getRegularComicByRegular_Comic_Id(change.getComic_id()).getPanel_id()).getAuthor();
            System.out.println("RUSERNA: " + username );

            Notification note = new Notification();
            note.setNotification_type(notification_type);
            note.setNotification(notification);
            note.setUsername(username);
            note.setNotifcation_time(now);
            note.setLink(Integer.toString(change.getComic_id()));
            this.notificationService.addNotification(note);
        }
        else{
            GameComic gc = gameComicService.getGameComicByGameComicId(change.getComic_id());
            String author1 = panelService.getPanelFromPanelId(gc.getPanel1_id()).getAuthor();
            String author2 = panelService.getPanelFromPanelId(gc.getPanel2_id()).getAuthor();
            String author3 = panelService.getPanelFromPanelId(gc.getPanel3_id()).getAuthor();
            String author4 = panelService.getPanelFromPanelId(gc.getPanel4_id()).getAuthor();

            Notification note = new Notification();
            note.setNotification_type(notification_type);
            note.setNotification(notification);
            note.setUsername(author1);
            note.setNotifcation_time(now);
            note.setLink(Integer.toString(change.getComic_id()));
            this.notificationService.addNotification(note);

            Notification note2 = new Notification();
            note2.setNotification_type(notification_type);
            note2.setNotification(notification);
            note2.setUsername(author2);
            note2.setNotifcation_time(now);
            note2.setLink(Integer.toString(change.getComic_id()));
            this.notificationService.addNotification(note2);

            Notification note3 = new Notification();
            note3.setNotification_type(notification_type);
            note3.setNotification(notification);
            note3.setUsername(author3);
            note3.setNotifcation_time(now);
            note3.setLink(Integer.toString(change.getComic_id()));
            this.notificationService.addNotification(note3);

            Notification note4 = new Notification();
            note4.setNotification_type(notification_type);
            note4.setNotification(notification);
            note4.setUsername(author4);
            note4.setNotifcation_time(now);
            note4.setLink(Integer.toString(change.getComic_id()));
            this.notificationService.addNotification(note4);
        }
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

        if (change.getComic_type() == 0) {
            String username = panelService.getPanelFromPanelId(regularComicService.getRegularComicByRegular_Comic_Id(change.getComic_id()).getPanel_id()).getAuthor();

            Notification note = new Notification();
            note.setNotification_type(notification_type);
            note.setNotification(notification);
            note.setUsername(username);
            note.setNotifcation_time(now);
            note.setLink(Integer.toString(change.getComic_id()));
            this.notificationService.addNotification(note);
        }
        else{
            GameComic gc = gameComicService.getGameComicByGameComicId(change.getComic_id());
            String author1 = panelService.getPanelFromPanelId(gc.getPanel1_id()).getAuthor();
            String author2 = panelService.getPanelFromPanelId(gc.getPanel2_id()).getAuthor();
            String author3 = panelService.getPanelFromPanelId(gc.getPanel3_id()).getAuthor();
            String author4 = panelService.getPanelFromPanelId(gc.getPanel4_id()).getAuthor();

            Notification note = new Notification();
            note.setNotification_type(notification_type);
            note.setNotification(notification);
            note.setUsername(author1);
            note.setNotifcation_time(now);
            note.setLink(Integer.toString(change.getComic_id()));
            this.notificationService.addNotification(note);

            Notification note2 = new Notification();
            note2.setNotification_type(notification_type);
            note2.setNotification(notification);
            note2.setUsername(author2);
            note2.setNotifcation_time(now);
            note2.setLink(Integer.toString(change.getComic_id()));
            this.notificationService.addNotification(note2);

            Notification note3 = new Notification();
            note3.setNotification_type(notification_type);
            note3.setNotification(notification);
            note3.setUsername(author3);
            note3.setNotifcation_time(now);
            note3.setLink(Integer.toString(change.getComic_id()));
            this.notificationService.addNotification(note3);

            Notification note4 = new Notification();
            note4.setNotification_type(notification_type);
            note4.setNotification(notification);
            note4.setUsername(author4);
            note4.setNotifcation_time(now);
            note4.setLink(Integer.toString(change.getComic_id()));
            this.notificationService.addNotification(note4);
        }
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
            if (c.getComic_id().equals(Integer.parseInt(comicId))){
                if(c.getComment_number().equals(Integer.parseInt(commentNum))){
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
            if (c.getComic_id().equals(Integer.parseInt(comicId))){
                if(c.getComment_number().equals(Integer.parseInt(commentNum))){
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
        note.setNotification_type(notification_type);
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
            if (reg.getRegular_comic_id().equals( p.getPanel_id())){
                return p.getAuthor();
            }
        }
        return "NO AUTHOR";
    }
}
