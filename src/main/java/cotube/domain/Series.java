package cotube.domain;
import javax.persistence.*;

@Entity
@Table(name = "series")
public class Series {

    @Id
    private Integer series_id;
    private String series_name;
    private Integer folder_id;

    public Series(){

    }

    public Series(Integer series_id, String series_name, Integer folder_id) {
        this.series_id = series_id;
        this.series_name = series_name;
        this.folder_id = folder_id;
    }

    public Integer getSeries_id() {
        return series_id;
    }

    public void setSeries_id(Integer series_id) {
        this.series_id = series_id;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }

    public Integer getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(Integer folder_id) {
        this.folder_id = folder_id;
    }

    @Override
    public String toString() {
        return "Series{" +
                "series_id=" + series_id +
                ", series_name='" + series_name + '\'' +
                ", folder_id=" + folder_id +
                '}';
    }
}