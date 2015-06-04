package edufarm.service;

import edufarm.viewModel.ErrorMessage;
import edufarm.viewModel.ValidationResponse;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * REST API 콘트롤러 계층에서 JSR303 검증이 실패할 경우, BindingResult 객체에 들어있는 fieldError를
 * {@link ValidationResponse}에 저장한다. {@link ValidationResponse}는
 * JSON String으로 인코딩되어 클라이언트에 Response로 보낸다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see ValidationResponse
 */
@Service
public class ValidationResponseService {
    public ValidationResponse setResponseFailed(ValidationResponse validationResponse, BindingResult bindingResult) {
        validationResponse.setStatus("FAIL");
        List<FieldError> allErrors = bindingResult.getFieldErrors();
        List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
        for (FieldError objectError : allErrors) {
            errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
        }
        validationResponse.setErrorMessageList(errorMesages);
        return validationResponse;
    }
}
