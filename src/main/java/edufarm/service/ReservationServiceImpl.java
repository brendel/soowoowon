package edufarm.service;

import edufarm.dao.CommentDAO;
import edufarm.dao.ReservationDAO;
import edufarm.exception.ResourceNotFoundException;
import edufarm.model.Article;
import edufarm.model.Comment;
import edufarm.model.Enum.ReservationState;
import edufarm.model.Reservation;
import edufarm.model.User;
import edufarm.viewModel.ReservationResponse;
import edufarm.viewModel.ReservationResponseForUser;
import edufarm.viewModel.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * {@link edufarm.service.ReservationService}의 구현.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.Reservation
 * @see edufarm.service.ReservationService
 */
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private SanitizerService sanitizerService;

    @Override
    public void add(Reservation reservation) {
        sanitizerService.clean(reservation);
        reservation.setReservationState(ReservationState.Pending);
        reservation.setPublished(new Date());
        reservationDAO.add(reservation);
    }

    @Override
    public void update(Reservation reservation) {
        sanitizerService.clean(reservation);
        reservationDAO.update(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationDAO.delete(reservation);
    }

    @Override
    public void changeState(int id, String state) {
        Reservation reservation = get(id);
        reservation.setReservationState(ReservationState.valueOf(state));
        update(reservation);
    }

    @Override
    public Comment getComment(int id) {
        return commentDAO.get(id);
    }

    @Override
    public void addComment(Comment comment) {
        sanitizerService.clean(comment);
        comment.setPublished(new Date());


        Article article = comment.getArticle();
        int commentCount = article.getCommentCount();
        article.setCommentCount(++commentCount);
        commentDAO.add(comment);
        reservationDAO.update((Reservation) article);
    }

    @Override
    public void deleteComment(Comment comment) {
        Article article = comment.getArticle();
        int commentCount = article.getCommentCount();
        article.setCommentCount(--commentCount);

        commentDAO.delete(comment);
        reservationDAO.update((Reservation) article);
    }

    @Override
    public int count() {
        return reservationDAO.count();
    }

    @Override
    public Reservation get(int id) {
        Reservation reservation = reservationDAO.get(id);
        if (reservation == null) {
            throw new ResourceNotFoundException("Reservation not found", id);
        }
        return reservation;
    }

    @Override
    public List<Reservation> list(User user) {
        return reservationDAO.list(user);
    }

    @Override
    public List<Reservation> list(Date from, Date to) {
        return reservationDAO.list(from, to);
    }

    @Override
    public List<Reservation> list(int first, int num) {
        return reservationDAO.list(first, num);
    }

    @Override
    public List<Reservation> list(SearchForm searchForm) {
        if (searchForm.getState() == -1)
            return reservationDAO.list(searchForm.getFrom(), searchForm.getTo());
        return reservationDAO.list(searchForm.getFrom(), searchForm.getTo(), searchForm.getState());
    }

    @Override
    public List<Reservation> list() {
        return reservationDAO.list();
    }

    @Override
    public ReservationResponse getResponseForUser(int id) {
        Reservation reservation = this.get(id);
        User user = reservation.getUser();
        ReservationResponse reservationResponse = new ReservationResponseForUser();
        reservationResponse.setId(reservation.getId());
        reservationResponse.setUserId(user.getId());
        reservationResponse.setDisplayName(user.getDisplayName());
        reservationResponse.setRole(user.getRole());

        reservationResponse.setTitle(reservation.getTitle());
        reservationResponse.setContent(reservation.getContent());
        reservationResponse.setAdult(reservation.getAdult());
        reservationResponse.setChild(reservation.getChild());
        reservationResponse.setState(reservation.getReservationState());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        reservationResponse.setDate(dateFormat.format(reservation.getDate()));
        dateFormat.applyPattern("yyyy-MM-dd HH:mm");
        reservationResponse.setPublished(dateFormat.format(reservation.getPublished()));
        return reservationResponse;
    }
}
