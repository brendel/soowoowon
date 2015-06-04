package edufarm.service;

import edufarm.model.Photo;
import edufarm.viewModel.UploadedPhoto;
import org.apache.commons.imaging.ImageReadException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * {@link edufarm.model.Photo}의 서비스 인터페이스.
 * <p>prepost 애노테이션이 붙어있는 메소드들은 특정 권한이 있어야만 사용가능하다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.Photo
 * @see edufarm.service.PhotoServiceImpl
 */
public interface PhotoService {

    @PostAuthorize("hasRole('ROLE_ADMIN') or authentication.name == returnObject.user.email")
    Photo getPhoto(int id);

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #photo.user.email")
    void savePhoto(Photo photo, UploadedPhoto uploadedPhoto) throws ImageReadException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #photo.user.email")
    void deletePhoto(Photo photo);

    List<Photo> list();
}
