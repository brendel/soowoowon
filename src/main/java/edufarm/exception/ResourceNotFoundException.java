package edufarm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 서비스 계층에서 DAO를 통해 특정 리소스에 접근했으나 존재하지 않았을 때 던져지는 예외.
 * 클라이언트로 HTTP 404를 보낸다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message, int id) {
        super(message + ": " + id);
    }
}
