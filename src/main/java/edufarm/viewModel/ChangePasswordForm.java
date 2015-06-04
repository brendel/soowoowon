package edufarm.viewModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 비밀번호를 변경할 때 클라이언트로부터 받는 폼 객체.
 * <p>기존의 비밀번호가 유효하면, 새 비밀번호를 {@link edufarm.model.User} 객체에 저장한다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.User
 */
public class ChangePasswordForm {

    @NotNull
    @Size(min = 6, message = "비밀번호가 틀렸습니다!")
    private String oldPassword;

    @NotNull
    @Size(min = 6, message = "비밀번호는 6자 이상으로 입력해주세요.")
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
