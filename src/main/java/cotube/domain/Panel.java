package cotube.domain;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "panel")
public class Panel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer panel_id;
    private String author;
    private String canvas_path;
    private String title_word;
    private Date date_created = new Date();

    public Panel(){

    }

    public Panel(String author, String canvas_path, String title_word, Date date_created) {
        this.author = author;
        this.canvas_path = canvas_path;
        this.title_word = title_word;
        this.date_created = date_created;
    }

    public Integer getPanel_id() {
        return panel_id;
    }

    public void setPanel_id(Integer panel_id) {
        this.panel_id = panel_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String authour) {
        this.author = authour;
    }

    public String getCanvas_path() {
        return canvas_path;
    }

    public void setCanvas_path(String canvas_path) {
        this.canvas_path = canvas_path;
    }

    public String getTitle_word() {
        return title_word;
    }

    public void setTitle_word(String title_word) {
        this.title_word = title_word;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    @Override
    public String toString() {
        return "Panel{" +
                "panel_id=" + panel_id +
                ", author='" + author + '\'' +
                ", canvas_path='" + canvas_path + '\'' +
                ", title_word='" + title_word + '\'' +
                ", date_created=" + date_created +
                '}';
    }
}
