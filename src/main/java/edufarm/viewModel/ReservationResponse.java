package edufarm.viewModel;

import edufarm.model.Enum.ReservationState;

/**
 * Created by jazzbach on 15. 3. 29.
 */
public interface ReservationResponse {
    int getId();

    void setId(int id);

    String getTitle();

    void setTitle(String title);

    String getContent();

    void setContent(String content);

    String getDate();

    void setDate(String date);

    String getPublished();

    void setPublished(String published);

    ReservationState getState();

    void setState(ReservationState reservationState);

    byte getPart();

    void setPart(byte part);

    int getAdult();

    void setAdult(int adult);

    int getChild();

    void setChild(int child);

    int getUserId();

    void setUserId(int userId);

    String getDisplayName();

    void setDisplayName(String displayName);

    byte getRole();

    void setRole(byte role);
}
