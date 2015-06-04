package edufarm.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * SMS 메세지를 나타내는 클래스.
 * <p>{@link edufarm.model.User}와 N:1 연관을 갖는다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.service.SmsService
 */
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date sent;

    @NotNull
    private String message;

    @NotNull
    @Size(min = 7, max = 100)
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "[_0-9a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{2,10}")
    private String displayName;

    @NotNull
    @Pattern(regexp = "^01[\\d]{8,9}")
    private String phone;
    /**
     * SMS 서비스 제공자에게 보낸 문자 전송 요청에 대한 응답 코드.
     */
    private String resultCode;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }
}
