package com.soowoowon.viewModel;

import com.soowoowon.validation.ContentType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * 클라이언트가 업로드한 사진을 나타내는 클래스.
 * <p>{@link com.soowoowon.validation.ContentType} 애노테이션에 의해 MultipartFile의 ContentType은
 * "image/jpg", "image/jpeg", "image/png" 세가지로 제한된다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.Photo
 * @see com.soowoowon.model.Article
 * @see ArticleForm
 */
public class UploadedPhoto {

    @NotNull
    @ContentType(value = {"image/jpg", "image/jpeg", "image/png"}, message = "PNG나 JPG 포멧 이미지를 올려주세요!")
    private MultipartFile photo;

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
