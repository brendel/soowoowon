package com.soowoowon.dao;

import com.soowoowon.model.Reservation;
import com.soowoowon.model.User;

import java.util.Date;
import java.util.List;

/**
 * {@link com.soowoowon.model.Reservation} 도메인 모델의 DAO.CRUD 인터페이스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.Reservation
 * @see com.soowoowon.dao.ReservationDAOHibernate
 */
public interface ReservationDAO {
    void add(Reservation reservation);

    void update(Reservation reservation);

    void delete(int id);

    void delete(Reservation reservation);

    int count();

    Reservation get(int id);

    List<Reservation> list(User user);

    List<Reservation> list(Date from, Date to);

    List<Reservation> list(Date from, Date to, byte state);

    List<Reservation> list(int first, int num);

    List<Reservation> list();
}
