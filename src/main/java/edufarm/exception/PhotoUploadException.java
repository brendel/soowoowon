package edufarm.exception;

/**
 * {@link edufarm.service.PhotoService}에서 이미지 업로드나 이미지 프로세싱 도중,
 * IOException이 발생하는 경우 던져지는 예외. RuntimeException의 단순 확장한 클래스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
public class PhotoUploadException extends RuntimeException {

    public PhotoUploadException(String message) {
        super(message);
    }

    public PhotoUploadException(String message, Throwable e) {
        super(message, e);
    }
}