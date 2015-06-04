package edufarm.viewModel;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * {@link edufarm.model.Article}의 검색조건을 나타내는 클래스.검색 폼(form)과 바인딩한다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
public class SearchForm {

    @NotNull
    private Date from;

    @NotNull
    private Date to;

    @Email
    private String email;

    private String displayName;

    @Range(min = -1, max = 3)
    private byte state;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

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

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}
