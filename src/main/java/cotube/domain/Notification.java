package cotube.domain;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    private Integer notification_id;
    private Integer notifcation_type;
    private String notification;
    private String username;
    private Date notifcation_time = new Date();;

    public Notification() {
    }

    public Notification(Integer notification_id, Integer notifcation_type, String notification, String username) {
        this.notification_id = notification_id;
        this.notifcation_type = notifcation_type;
        this.notification = notification;
        this.username = username;
    }

    public Integer getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(Integer notification_id) {
        this.notification_id = notification_id;
    }

    public Integer getNotifcation_type() {
        return notifcation_type;
    }

    public void setNotifcation_type(Integer notifcation_type) {
        this.notifcation_type = notifcation_type;
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
        return notifcation_time;
    }

    public void setNotifcation_time(Date notifcation_time) {
        this.notifcation_time = notifcation_time;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notification_id=" + notification_id +
                ", notifcation_type=" + notifcation_type +
                ", notification='" + notification + '\'' +
                ", username='" + username + '\'' +
                ", notifcation_time=" + notifcation_time +
                '}';
    }
}