package cotube.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer notification_id;
    private Integer notification_type;
    private String notification;
    private String username;
    private Date notification_time = new Date();;
    private String link = "";
    public Notification() {
    }

    public Notification(Integer notification_id, Integer notifcation_type, String notification, String username,String link) {
        this.notification_id = notification_id;
        this.notification_type = notifcation_type;
        this.notification = notification;
        this.username = username;
        this.link = link;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link){
        this.link = link;
    }

    public Integer getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(Integer notification_id) {
        this.notification_id = notification_id;
    }

    public Integer getNotification_type() {
        return notification_type;
    }

    public void setNotification_type(Integer notification_type) {
        this.notification_type = notification_type;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getNotifcation_time() {
        return notification_time;
    }

    public void setNotifcation_time(Date notifcation_time) {
        this.notification_time = notifcation_time;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notification_id=" + notification_id +
                ", notifcation_type=" + notification_type +
                ", notification='" + notification + '\'' +
                ", username='" + username + '\'' +
                ", notifcation_time=" + notification_time +
                '}';
    }
}