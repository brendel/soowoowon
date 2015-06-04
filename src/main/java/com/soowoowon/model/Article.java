package com.soowoowon.model;

import com.soowoowon.model.Enum.ArticleState;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * 모든 게시물 타입의 기본형이 되는 클래스.
 * <p>같은 이름의 DB 테이블(Article)과 맵핑된다. {@link com.soowoowon.model.User}와 N:1 연관을 가지며,
 * {@link com.soowoowon.model.Comment}와 선택적 1:N 연관을 가진다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "User_Article",
            joinColumns = {@JoinColumn(name = "Article_ID")},
            inverseJoinColumns = {@JoinColumn(name = "User_ID")}
    )
    private User user;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @OrderBy("published")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @OrderBy("published")
    private Set<Photo> photos = new HashSet<>();

    @NotNull
    @Min(0)
    private int commentCount = 0;

    @NotNull
    private boolean commentStatus = true;

    @NotNull
    @Size(min = 2, max = 100)
    private String title;

    @NotNull
    @Size(min = 2, max = 50000)
    private String content;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date published;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

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
    @Enumerated(EnumType.STRING)
    private ArticleState articleState;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {

        this.published = published;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public boolean isCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(boolean commentStatus) {
        this.commentStatus = commentStatus;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
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

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public ArticleState getArticleState() {
        return articleState;
    }

    public void setArticleState(ArticleState articleState) {
        this.articleState = articleState;
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
