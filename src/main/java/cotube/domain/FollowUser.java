package cotube.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "followUser")
@IdClass(FollowUser.IdClass.class)
public class FollowUser {
    @Id
    public String follower_username;
    @Id
    public String following_username;
    public Date follow_time = new Date();

    public FollowUser(){

    }

    public FollowUser(String follower_username, String following_username, Date follow_time) {
        this.follower_username = follower_username;
        this.following_username = following_username;
        this.follow_time = follow_time;
    }

    public String getFollower_username() {
        return follower_username;
    }

    public void setFollower_username(String follower_username) {
        this.follower_username = follower_username;
    }

    public String getFollowing_username() {
        return following_username;
    }

    public void setFollowing_username(String following_username) {
        this.following_username = following_username;
    }

    public Date getFollow_time() {
        return follow_time;
    }

    public void setFollow_time(Date follow_time) {
        this.follow_time = follow_time;
    }


    @Override
    public String toString() {
        return "FollowUser{" +
                "follower_username='" + follower_username + '\'' +
                ", following_username='" + following_username + '\'' +
                ", follow_time=" + follow_time +
                '}';
    }


    static class IdClass implements Serializable {
        public String follower_username;
        public String following_username;
    }
}