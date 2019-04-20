package cotube.domain;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "folder")
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer folder_id;
    private String username;
    private String folder_name;
    private Integer folder_type;
    private Integer visibility;

    public Folder() {

    }

    public Folder(String username, String folder_name, Integer folder_type, Integer visibility) {
        this.username = username;
        this.folder_name = folder_name;
        this.folder_type = folder_type;
        this.visibility = visibility;
    }

    public Integer getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(Integer folder_id) {
        this.folder_id = folder_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public Integer getFolder_type() {
        return folder_type;
    }

    public void setFolder_type(Integer folder_type) {
        this.folder_type = folder_type;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "folder_id=" + folder_id +
                ", username='" + username + '\'' +
                ", folder_name='" + folder_name + '\'' +
                ", folder_type=" + folder_type +
                ", visibility=" + visibility +
                '}';
    }
}
