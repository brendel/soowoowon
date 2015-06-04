package edufarm.model;

import edufarm.model.Enum.ReservationState;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 하나의 예약을 게시물을 나타내는 클래스.
 * <p>같은 이름의 DB 테이블(Reservation)과 맵핑된다. {@link edufarm.model.Article}을 확장하여 다형적 1:1 연관을 가지고 있다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.service.ReservationService
 * @see edufarm.model.Article
 * @see edufarm.model.Comment
 */
@Entity
@PrimaryKeyJoinColumn(name = "article_id")
public class Reservation extends Article {

    @NotNull
    @Pattern(regexp = "^01[\\d]{8,9}")
    private String phone;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    /**
     * 예약 시간대. 1부/2부
     */
    @NotNull
    @Min(1)
    @Max(2)
    private byte part;

    /**
     * 어른 인원
     */
    @NotNull
    @Min(0)
    private int adult;

    /**
     * 아동 인원
     */
    @NotNull
    @Min(0)
    private int child;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ReservationState reservationState;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte getPart() {
        return part;
    }

    public void setPart(byte part) {
        this.part = part;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public ReservationState getReservationState() {
        return reservationState;
    }

    public void setReservationState(ReservationState reservationState) {
        this.reservationState = reservationState;
    }
}
