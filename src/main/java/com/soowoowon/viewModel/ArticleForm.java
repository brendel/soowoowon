package com.soowoowon.viewModel;

import com.soowoowon.model.Article;
import com.soowoowon.service.ArticleService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 클라이언트로부터 받는 article의 폼(form) 객체.
 * <p>실제 사진후기(article)의 작성은 사진의 업로드와 게시글의 작성이 Ajax 요청으로 따로따로 분리되어 발생하게 되므로,
 * {@link com.soowoowon.model.Article}로는 올바르게 폼데이터를 바인딩할 수 없다.
 * 하나의 {@link com.soowoowon.model.Article}는 여러 번의 multipart/form-data 요청과 본문인 articleForm을 POST 요청으로 나눠서 받은 다음,
 * {@link ArticleService#add(Article, ArticleForm)}를 통해 하나의 {@link com.soowoowon.model.Article}로 저장된다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.Article
 * @see com.soowoowon.model.Photo
 * @see UploadedPhoto
 */
public class ArticleForm {

    @NotNull(message = "제목을 입력해 주세요!")
    private String title;

    @NotNull(message = "글 내용을 입력해 주세요!")
    private String content;

    private List<Integer> uploadedPhotos;

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

    public List<Integer> getUploadedPhotos() {
        return uploadedPhotos;
    }

    public void setUploadedPhotos(List<Integer> uploadedPhotos) {
        this.uploadedPhotos = uploadedPhotos;
    }
}
