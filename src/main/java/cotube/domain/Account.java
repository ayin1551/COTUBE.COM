package cotube.domain;
import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String security_question;
    private String security_answer;
    private String profile_pic_path;
    private String account_role;

    public Account(){

    }

    public Account(String username, String password, String security_question, String security_answer, String profile_pic_path, String account_role) {
        this.username = username;
        this.password = password;
        this.security_question = security_question;
        this.security_answer = security_answer;
        this.profile_pic_path = profile_pic_path;
        this.account_role = account_role;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurity_question() {
        return security_question;
    }

    public void setSecurity_question(String security_question) {
        this.security_question = security_question;
    }

    public String getSecurity_answer() {
        return security_answer;
    }

    public void setSecurity_answer(String security_answer) {
        this.security_answer = security_answer;
    }

    public String getProfile_pic_path() {
        return profile_pic_path;
    }

    public void setProfile_pic_path(String profile_pic_path) {
        this.profile_pic_path = profile_pic_path;
    }

    public String getAccount_role() {
        return account_role;
    }

    public void setAccount_role(String account_role) {
        this.account_role = account_role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", security_question='" + security_question + '\'' +
                ", security_answer='" + security_answer + '\'' +
                ", profile_pic_path='" + profile_pic_path + '\'' +
                ", account_role='" + account_role + '\'' +
                '}';
    }

}