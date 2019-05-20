package cotube.controller;

import cotube.domain.Account;
import cotube.domain.Comic;
import cotube.domain.Notification;
import cotube.services.AccountService;
import cotube.services.ComicService;
import cotube.services.NotificationService;
import org.aspectj.weaver.ast.Not;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/message.html")
public class ajaxMessageController{

    private NotificationService notificationService;
    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    private ComicService comicService;
    @Autowired
    public void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }


    @RequestMapping(value="/getMessages",method = RequestMethod.POST)
    @ResponseBody
    public String getMessages(HttpServletRequest request){
        //Username returns "" so we will use all of them until we can solve this

        String username = request.getParameter("username");
        List<Notification> all = this.notificationService.getAllNotifications();
        List<Notification> result = new ArrayList<>();

        for(Notification note: all){
            System.out.println(note.getUsername());
            if (note.getUsername().equals(username)) {
                result.add(note);
            }
        }
        JSONObject go = new JSONObject();
        go.put("MESSAGES",result);
        return go.toString();
    }

    @RequestMapping(value="/deleteComment",method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteComment(HttpServletRequest request){
        //Username returns "" so we will use all of them until we can solve this

        String str = request.getParameter("id");
        int id = Integer.parseInt(str);
        List<Notification> all = this.notificationService.getAllNotifications();
        Notification delete = new Notification();
        for(Notification note: all){
            if (note.getNotification_id().equals(id)) {
                delete = note;
            }
        }

        this.notificationService.deleteNotification(delete);
        System.out.println("a");
        return true;
    }

    @RequestMapping(value="/getType",method = RequestMethod.POST)
    @ResponseBody
    public Integer getType(HttpServletRequest request){
        //Username returns "" so we will use all of them until we can solve this

        String str = request.getParameter("id");
        int id = Integer.parseInt(str);
        Comic comic = comicService.getComicByComic_Id(id);
        return comic.getComic_type();
    }

}