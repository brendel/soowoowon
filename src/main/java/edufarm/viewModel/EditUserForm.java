package edufarm.viewModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 회원 정보 수정 폼(form).
 * <p>클라이언트로부터 받은 수정된 회원정보가 유효하면, {@link edufarm.model.User}객체에 저장한다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.User
 */
public class EditUserForm {

    @NotNull
    @Pattern(regexp = "[_0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{2,10}", message = "올바른 이름이 아닙니다(2자 이상 8자 이하, 특수문자 제외)")
    private String displayName;

    @NotNull
    @Pattern(regexp = "^01[\\d]{8,9}", message = "올바른 핸드폰번호가 아닙니다. 다시 확인해주세요!")
    private String phone;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
