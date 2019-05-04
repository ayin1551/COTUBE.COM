package cotube.domain;
import javax.persistence.*;

@Entity
@Table(name = "gameComic")
public class GameComic {

    @Id
    private Integer game_comic_id;
    private String keyword;
    private Integer gamecomic_type;
    private Integer panel1_id;
    private Integer panel2_id;
    private Integer panel3_id;
    private Integer panel4_id;

    public GameComic() {
    }

    public GameComic(Integer game_comic_id, String keyword, Integer gamecomic_type, Integer panel1_id, Integer panel2_id, Integer panel3_id, Integer panel4_id) {
        this.game_comic_id = game_comic_id;
        this.keyword = keyword;
        this.gamecomic_type = gamecomic_type;
        this.panel1_id = panel1_id;
        this.panel2_id = panel2_id;
        this.panel3_id = panel3_id;
        this.panel4_id = panel4_id;
    }

    public Integer getGame_comic_id() {
        return game_comic_id;
    }

    public void setGame_comic_id(Integer game_comic_id) {
        this.game_comic_id = game_comic_id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getGamecomic_type() {
        return gamecomic_type;
    }

    public void setGamecomic_type(Integer gamecomic_type) {
        this.gamecomic_type = gamecomic_type;
    }

    public Integer getPanel1_id() {
        return panel1_id;
    }

    public void setPanel1_id(Integer panel1_id) {
        this.panel1_id = panel1_id;
    }

    public Integer getPanel2_id() {
        return panel2_id;
    }

    public void setPanel2_id(Integer panel2_id) {
        this.panel2_id = panel2_id;
    }

    public Integer getPanel3_id() {
        return panel3_id;
    }

    public void setPanel3_id(Integer panel3_id) {
        this.panel3_id = panel3_id;
    }

    public Integer getPanel4_id() {
        return panel4_id;
    }

    public void setPanel4_id(Integer panel4_id) {
        this.panel4_id = panel4_id;
    }

    @Override
    public String toString() {
        return "GameComic{" +
                "game_comic_id='" + game_comic_id + '\'' +
                ", keyword='" + keyword + '\'' +
                ", gamecomic_type=" + gamecomic_type +
                ", panel1_id=" + panel1_id +
                ", panel2_id=" + panel2_id +
                ", panel3_id=" + panel3_id +
                ", panel4_id=" + panel4_id +
                '}';
    }
}