package edufarm.controller.rest;

import edufarm.model.User;
import edufarm.service.UserService;
import edufarm.service.ValidationResponseService;
import edufarm.viewModel.ErrorMessage;
import edufarm.viewModel.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 회원 가입 콘드롤러.
 */
@Controller
@RequestMapping("/api/signup")
public class SignUpRestController {

    @Autowired
    protected AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ValidationResponseService validationResponseService;


    /**
     * 회원 가입.
     * <p>JSON String으로 인코딩 된 {@link edufarm.model.User} 객체를 받는다(HTTP POST).
     * 유효성 검증 성공시 status: "SUCCESS", 실패시 status: "FAIL", 추가로 검증을 통과하지 못한 필드와 에러메세지를
     * JSON String 으로 인코딩하여 응답.
     *
     * @param user
     * @param result
     * @return
     * @author Kiwon Lee <bwv1050@gmail.com>
     * @see ValidationResponse
     */
    @RequestMapping(method = RequestMethod.POST, consumes = {"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public ValidationResponse submit(@Valid @RequestBody User user, BindingResult result) {
        ValidationResponse validationResponse = new ValidationResponse();
        if (result.hasErrors())
            return validationResponseService.setResponseFailed(validationResponse, result);

        if (userService.isExist(user.getEmail())) {
            validationResponse.setStatus("FAIL");
            List<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();
            errorMessageList.add(new ErrorMessage("email", "이미 사용 중인 아이디입니다."));
            validationResponse.setErrorMessageList(errorMessageList);
            return validationResponse;
        }

        validationResponse.setStatus("SUCCESS");
        String password = user.getPassword();
        userService.add(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(auth);

        if (auth.isAuthenticated())
            SecurityContextHolder.getContext().setAuthentication(auth);

        return validationResponse;
    }
}
