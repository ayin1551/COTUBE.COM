package cotube.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "followSeries")
@IdClass(FollowSeries.IdClass.class)
public class FollowSeries{
    @Id
    private String follower_username;
    @Id
    private Integer series_id;
    private Date follow_time = new Date();

    public FollowSeries(){

    }

    public FollowSeries(String follower_username, Integer series_id, Date follow_time) {
        this.follower_username = follower_username;
        this.series_id = series_id;
        this.follow_time = follow_time;
    }

    public String getFollower_username() {
        return follower_username;
    }

    public void setFollower_username(String follower_username) {
        this.follower_username = follower_username;
    }

    public Integer getSeries_id() {
        return series_id;
    }

    public void setSeries_id(Integer series_id) {
        this.series_id = series_id;
    }

    public Date getFollow_time() {
        return follow_time;
    }

    public void setFollow_time(Date follow_time) {
        this.follow_time = follow_time;
    }

    @Override
    public String toString() {
        return "FollowSeries{" +
                "follower_username='" + follower_username + '\'' +
                ", series_id=" + series_id +
                ", follow_time=" + follow_time +
                '}';
    }

    static class IdClass implements Serializable {
        public String follower_username;
        public Integer series_id;
    }
}
