package cotube.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "favorite")
@IdClass(Favorite.IdClass.class)
public class Favorite {
    @Id
    private Integer comic_id;
    @Id
    private String favoriter_username;
    private Date favorite_time = new Date();
    @Id
    private Integer favorite_folder_id;

    public Favorite(){

    }

    public Favorite(Integer comic_id, String favoriter_username, Date favorite_time, Integer favorite_folder_id) {
        this.comic_id = comic_id;
        this.favoriter_username = favoriter_username;
        this.favorite_time = favorite_time;
        this.favorite_folder_id = favorite_folder_id;
    }

    public Integer getComic_id() {
        return comic_id;
    }

    public void setComic_id(Integer comic_id) {
        this.comic_id = comic_id;
    }

    public String getFavoriter_username() {
        return favoriter_username;
    }

    public void setFavoriter_username(String favoriter_username) {
        this.favoriter_username = favoriter_username;
    }

    public Date getFavorite_time() {
        return favorite_time;
    }

    public void setFavorite_time(Date favorite_time) {
        this.favorite_time = favorite_time;
    }

    public Integer getFavorite_folder_id() {
        return favorite_folder_id;
    }

    public void setFavorite_folder_id(Integer favorite_folder_id) {
        this.favorite_folder_id = favorite_folder_id;
    }
    @Override
    public String toString() {
        return "Favorite{" +
                "comic_id=" + comic_id +
                ", favoriter_username='" + favoriter_username + '\'' +
                ", favorite_time=" + favorite_time +
                ", favorite_folder_id='" + favorite_folder_id + '\'' +
                '}';
    }

    static class IdClass implements Serializable {
        public Integer comic_id;
        public String favoriter_username;
        public Integer favorite_folder_id;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IdClass idClass = (IdClass) o;
            return Objects.equals(comic_id, idClass.comic_id) &&
                    Objects.equals(favoriter_username, idClass.favoriter_username) &&
                    Objects.equals(favorite_folder_id, idClass.favorite_folder_id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(comic_id, favoriter_username, favorite_folder_id);
        }
    }
}
