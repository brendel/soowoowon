package edufarm.model;

import edufarm.service.ArticleService;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 하나의 댓글을 나타내는 클래스.
 * <p>같은 이름의 DB 테이블(Comment)과 맵핑된다. {@link edufarm.model.User}와 N:1 연관을 가지며,
 * {@link edufarm.model.Article}과 조인테이블 "Article_Comment"를 통해 선택적 N:1 연관을 가진다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.service.ReservationService
 * @see ArticleService
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "User_Comment",
            joinColumns = {@JoinColumn(name = "Comment_ID")},
            inverseJoinColumns = {@JoinColumn(name = "User_ID")}
    )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date published;

    @NotNull
    @Size(min = 2, max = 500)
    private String content;

    @NotNull
    @Size(min = 7, max = 100)
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "[_0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{2,10}")
    private String displayName;

    @Size(min = 4, max = 20)
    private String password;

    @NotNull
    private boolean approved;

    private String remoteIP;

    private String remoteOS;

    private String remoteBrowser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    public String getRemoteOS() {
        return remoteOS;
    }

    public void setRemoteOS(String remoteOS) {
        this.remoteOS = remoteOS;
    }

    public String getRemoteBrowser() {
        return remoteBrowser;
    }

    public void setRemoteBrowser(String remoteBrowser) {
        this.remoteBrowser = remoteBrowser;
    }
}
