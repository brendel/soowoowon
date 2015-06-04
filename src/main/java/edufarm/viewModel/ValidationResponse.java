package edufarm.viewModel;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * 클라이언트가 보낸 폼 객체를 검증한 다음, 클라이언트로 되돌려보내는 Response를 나타내는 클래스.
 * <p>상태(status)와 에러메세지 객체({@link ErrorMessage})의 리스트로 구성되어있다.
 * 클라이언트로 응답을 보낼 때는 보통 JSON String으로 인코딩된다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see ErrorMessage
 * @see edufarm.service.ValidationResponseService
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationResponse {
    private String status;
    private List<ErrorMessage> errorMessageList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ErrorMessage> getErrorMessageList() {
        return this.errorMessageList;
    }

    public void setErrorMessageList(List<ErrorMessage> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }
}