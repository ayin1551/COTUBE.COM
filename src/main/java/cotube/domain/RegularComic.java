package cotube.domain;
import javax.persistence.*;

@Entity
@Table(name = "regularComic")
public class RegularComic {

    @Id
    private Integer regular_comic_id;
    private Integer series_id;
    private String thumbnail_path;
    private String description;
    private Integer panel_id;

    public RegularComic(){

    }

    public RegularComic(Integer regular_comic_id, Integer series_id, String thumbnail_path, String description, Integer panel_id) {
        this.regular_comic_id = regular_comic_id;
        this.series_id = series_id;
        this.thumbnail_path = thumbnail_path;
        this.description = description;
        this.panel_id = panel_id;
    }

    public Integer getRegular_comic_id() {
        return regular_comic_id;
    }

    public void setRegular_comic_id(Integer regular_comic_id) {
        this.regular_comic_id = regular_comic_id;
    }

    public Integer getSeries_id() {
        return series_id;
    }

    public void setSeries_id(Integer series_id) {
        this.series_id = series_id;
    }

    public String getThumbnail_path() {
        return thumbnail_path;
    }

    public void setThumbnail_path(String thumbnail_path) {
        this.thumbnail_path = thumbnail_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPanel_id() {
        return panel_id;
    }

    public void setPanel_id(Integer panel_id) {
        this.panel_id = panel_id;
    }

    @Override
    public String toString() {
        return "RegularComic{" +
                "regular_comic_id=" + regular_comic_id +
                ", series_id=" + series_id +
                ", thumbnail_path='" + thumbnail_path + '\'' +
                ", description='" + description + '\'' +
                ", panel_id=" + panel_id +
                '}';
    }
}