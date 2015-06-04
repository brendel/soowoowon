package com.soowoowon.service;

import com.soowoowon.model.Comment;
import com.soowoowon.model.Reservation;
import com.soowoowon.model.User;
import com.soowoowon.viewModel.ReservationResponse;
import com.soowoowon.viewModel.SearchForm;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Date;
import java.util.List;

/**
 * {@link com.soowoowon.model.Reservation}의 서비스 인터페이스.
 * <p>prepost 애노테이션이 붙어있는 메소드들은 특정 권한이 있어야만 사용가능하다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.Reservation
 * @see com.soowoowon.service.ReservationServiceImpl
 */
public interface ReservationService {

    @PostAuthorize("hasRole('ROLE_ADMIN') or authentication.name == returnObject.user.email")
    Reservation get(int id);

    @PreAuthorize("hasRole('ROLE_USER')")
    void add(Reservation reservation);

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #reservation.user.email")
    void update(Reservation reservation);

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #reservation.user.email")
    void delete(Reservation reservation);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void changeState(int id, String state);

    @PostAuthorize("hasRole('ROLE_ADMIN') or authentication.name == returnObject.user.email")
    Comment getComment(int id);

    @PreAuthorize("hasRole('ROLE_USER')")
    void addComment(Comment comment);

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #comment.user.email")
    void deleteComment(Comment comment);

    int count();

    List<Reservation> list(User user);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<Reservation> list(Date from, Date to);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<Reservation> list(SearchForm searchForm);

    List<Reservation> list(int first, int num);

    List<Reservation> list();

    ReservationResponse getResponseForUser(int id);
}
