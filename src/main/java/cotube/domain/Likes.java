package cotube.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "likes")
@IdClass(Likes.IdClass.class)
public class Likes {
    @Id
    private Integer comic_id;
    @Id
    private String liker_username;
    private Date like_time = new Date();

    public Likes(){

    }

    public Likes(Integer comic_id, String liker_username, Date like_time) {
        this.comic_id = comic_id;
        this.liker_username = liker_username;
        this.like_time = like_time;
    }

    public Integer getComic_id() {
        return comic_id;
    }

    public void setComic_id(Integer comic_id) {
        this.comic_id = comic_id;
    }

    public String getLiker_username() {
        return liker_username;
    }

    public void setLiker_username(String liker_username) {
        this.liker_username = liker_username;
    }

    public Date getLike_time() {
        return like_time;
    }

    public void setLike_time(Date like_time) {
        this.like_time = like_time;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "comic_id=" + comic_id +
                ", liker_username='" + liker_username + '\'' +
                ", like_time=" + like_time +
                '}';
    }

    static class IdClass implements Serializable {
        public Integer comic_id;
        public String liker_username;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IdClass idClass = (IdClass) o;
            return Objects.equals(comic_id, idClass.comic_id) &&
                    Objects.equals(liker_username, idClass.liker_username);
        }

        @Override
        public int hashCode() {
            return Objects.hash(comic_id, liker_username);
        }
    }
}
