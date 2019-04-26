package cotube.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "comments")
@IdClass(Comments.IdClass.class)
public class Comments {
    @Id
    private Integer comic_id;
    @Id
    private Integer comment_number;
    private String commenter_username;
    private Date comment_time = new Date();
    private String comment;
    private Integer status;

    public Comments(){

    }

    public Comments(Integer comic_id, Integer comment_number, String commenter_username, Date comment_time, String comment, Integer status) {
        this.comic_id = comic_id;
        this.comment_number = comment_number;
        this.commenter_username = commenter_username;
        this.comment_time = comment_time;
        this.comment = comment;
        this.status = status;
    }

    public Integer getComic_id() {
        return comic_id;
    }

    public void setComic_id(Integer comic_id) {
        this.comic_id = comic_id;
    }

    public Integer getComment_number() {
        return comment_number;
    }

    public void setComment_number(Integer comment_number) {
        this.comment_number = comment_number;
    }

    public String getCommenter_username() {
        return commenter_username;
    }

    public void setUsername(String commenter_username) {
        this.commenter_username = commenter_username;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "comic_id=" + comic_id +
                ", comment_number=" + comment_number +
                ", commenter_username='" + commenter_username + '\'' +
                ", comment_time=" + comment_time +
                ", comment='" + comment + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    static class IdClass implements Serializable {
        public Integer comic_id;
        public Integer comment_number;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IdClass idClass = (IdClass) o;
            return comic_id.equals(idClass.comic_id) &&
                    comment_number.equals(idClass.comment_number);
        }

        @Override
        public int hashCode() {
            return Objects.hash(comic_id, comment_number);
        }
    }
}
