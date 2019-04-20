package cotube.domain;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comic")
public class Comic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer comic_id;
    private Integer comic_type;
    private String title;
    private Integer status;
    private Date date_published = new Date();

    public Comic() {
    }

    public Comic(Integer comic_type, String title, Integer status, Date date_published) {
        this.comic_type = comic_type;
        this.title = title;
        this.status = status;
        this.date_published = date_published;
    }

    public Integer getComic_id() {
        return comic_id;
    }

    public void setComic_id(Integer comic_id) {
        this.comic_id = comic_id;
    }

    public Integer getComic_type() {
        return comic_type;
    }

    public void setComic_type(Integer comic_type) {
        this.comic_type = comic_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDate_published() {
        return date_published;
    }

    public void setDate_published(Date date_published) {
        this.date_published = date_published;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "comic_id=" + comic_id +
                ", comic_type='" + comic_type + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", date_published=" + date_published +
                '}';
    }
}