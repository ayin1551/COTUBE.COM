package cotube.domain;
import javax.persistence.*;

@Entity
@Table(name = "keyword")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer keyword_id;
    private String keyword;

    public Keyword() {
    }

    public Keyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getKeyword_id() {
        return keyword_id;
    }

    public void setKeyword_id(Integer keyword_id) {
        this.keyword_id = keyword_id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Keyword{" +
                "keyword_id=" + keyword_id +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}

