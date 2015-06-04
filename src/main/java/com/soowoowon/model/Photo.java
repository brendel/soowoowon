package com.soowoowon.model;

import com.soowoowon.viewModel.UploadedPhoto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 유저가 업로드한 사진의 정보를 나타내는 클래스.
 * <p/>
 * <p>같은 이름의 DB 테이블(Photo)과 맵핑된다. {@link com.soowoowon.model.User}와 N:1 연관을 가지며,
 * {@link com.soowoowon.model.Article}와 조인테이블 "Article_Photo"를 통해 선택적 N:1 연관을 가진다.
 *
 * @author Kiwon Lee <bwv1050@ mail.com>
 * @see com.soowoowon.service.PhotoService
 * @see UploadedPhoto
 * @see com.soowoowon.model.Photo
 */
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Article_Photo",
            joinColumns = {@JoinColumn(name = "Photo_ID")},
            inverseJoinColumns = {@JoinColumn(name = "Article_ID")}
    )
    private Article article;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date published;

    @NotNull
    private String uri;

    @NotNull
    private String thumbnailUri;

    @NotNull
    private String fileName;

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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    public void setThumbnailUri(String thumbnailUri) {
        this.thumbnailUri = thumbnailUri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
