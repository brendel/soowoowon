package edufarm.viewModel;

/**
 * Created by jazzbach on 15. 3. 29.
 */
public class ReservationResponseForAdmin extends ReservationResponseForUser implements ReservationResponse {

    private String phone;
    private String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
