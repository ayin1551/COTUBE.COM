package cotube.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "views")
@IdClass(Views.IdClass.class)
public class Views {
    @Id
    private Integer comic_id;
    @Id
    private String viewer_username;
    private Date view_time = new Date();

    public Views(){

    }

    public Views(Integer comic_id, String viewer_username, Date view_time) {
        this.comic_id = comic_id;
        this.viewer_username = viewer_username;
        this.view_time = view_time;
    }

    public Integer getComic_id() {
        return comic_id;
    }

    public void setComic_id(Integer comic_id) {
        this.comic_id = comic_id;
    }

    public String getViewer_username() {
        return viewer_username;
    }

    public void setViewer_username(String viewer_username) {
        this.viewer_username = viewer_username;
    }

    public Date getView_time() {
        return view_time;
    }

    public void setView_time(Date view_time) {
        this.view_time = view_time;
    }

    @Override
    public String toString() {
        return "Views{" +
                "comic_id=" + comic_id +
                ", viewer_username='" + viewer_username + '\'' +
                ", view_time=" + view_time +
                '}';
    }

    static class IdClass implements Serializable {
        public Integer comic_id;
        public String viewer_username;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IdClass idClass = (IdClass) o;
            return Objects.equals(comic_id, idClass.comic_id) &&
                    Objects.equals(viewer_username, idClass.viewer_username);
        }

        @Override
        public int hashCode() {
            return Objects.hash(comic_id, viewer_username);
        }
    }
}
