package com.soowoowon.controller.rest;

import com.soowoowon.model.User;
import com.soowoowon.service.UserService;
import com.soowoowon.service.ValidationResponseService;
import com.soowoowon.viewModel.ChangePasswordForm;
import com.soowoowon.viewModel.EditUserForm;
import com.soowoowon.viewModel.ErrorMessage;
import com.soowoowon.viewModel.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * 회원({@link com.soowoowon.model.User})정보의 수정, 패스워드 변경.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
@Controller
@RequestMapping("/api/userinfo")
public class UserInformationRestController {

    @Autowired
    UserService userService;

    @Autowired
    ValidationResponseService validationResponseService;


    /**
     * 회원({@link com.soowoowon.model.User}) 닉네임, 전화번호 변경(HTTP POST).
     * <p>JSON 인코딩된 {@link EditUserForm}을 받는다.
     *
     * @param editUserForm
     * @param result
     * @param principal
     * @return
     * @see ValidationResponse
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST, consumes = {"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public ValidationResponse editUser(@Valid @RequestBody EditUserForm editUserForm, BindingResult result, Principal principal) {
        ValidationResponse validationResponse = new ValidationResponse();
        if (result.hasErrors())
            return validationResponseService.setResponseFailed(validationResponse, result);

        User user = userService.get(principal.getName());

        user.setDisplayName(editUserForm.getDisplayName());
        user.setPhone(editUserForm.getPhone());
        userService.update(user);

        validationResponse.setStatus("SUCCESS");
        return validationResponse;
    }

    /**
     * 회원({@link com.soowoowon.model.User}) 비밀번호 변경(HTTP POST).
     * <p>JSON 인코딩 된 {@link ChangePasswordForm}을 받는다.
     *
     * @param passwordForm
     * @param result
     * @param principal
     * @return
     */
    @RequestMapping(value = "/password", method = RequestMethod.POST, consumes = {"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public ValidationResponse changePassword(@Valid @RequestBody ChangePasswordForm passwordForm, BindingResult result, Principal principal) {
        ValidationResponse validationResponse = new ValidationResponse();
        if (result.hasErrors())
            return validationResponseService.setResponseFailed(validationResponse, result);

        User user = userService.get(principal.getName());
        boolean passwordChanged = userService.updatePassword(user, passwordForm.getOldPassword(), passwordForm.getNewPassword());
        if (passwordChanged) {
            validationResponse.setStatus("SUCCESS");
        } else {
            validationResponse.setStatus("FAIL");
            List<ErrorMessage> errorMessageList = new ArrayList<ErrorMessage>();
            errorMessageList.add(new ErrorMessage("oldPassword", "비밀번호가 틀렸습니다. 다시 확인해 주세요."));
            validationResponse.setErrorMessageList(errorMessageList);
        }
        return validationResponse;
    }
}
