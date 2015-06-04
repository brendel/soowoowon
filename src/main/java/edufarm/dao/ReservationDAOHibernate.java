package edufarm.dao;

import edufarm.model.Reservation;
import edufarm.model.User;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * {@link edufarm.dao.ReservationDAO}의 Hibernate 구현.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.Reservation
 * @see edufarm.dao.ReservationDAO
 */
@Repository
public class ReservationDAOHibernate implements ReservationDAO {

    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory sessionFactory;

    @Override
    public void add(Reservation reservation) {
        getSession().save(reservation);
    }

    @Override
    public void update(Reservation reservation) {
        getSession().update(reservation);
    }

    @Override
    public void delete(int id) {
        getSession().createQuery("delete from Reservation where id = ?")
                .setInteger(0, id)
                .executeUpdate();
    }

    @Override
    public void delete(Reservation reservation) {
        getSession().delete(reservation);
    }

    @Override
    public int count() {
        return ((Long) getCriteria().setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public Reservation get(int id) {
        return (Reservation) getCriteria()
                .add(Restrictions.eq("id", id))
                .setFetchMode("comments", FetchMode.JOIN)
                .uniqueResult();
    }

    @Override
    public List<Reservation> list(User user) {
        return getCriteria()
                .add(Restrictions.eq("user", user))
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public List<Reservation> list(Date from, Date to) {
        return getCriteria()
                .add(Restrictions.between("date", from, to))
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public List<Reservation> list(Date from, Date to, byte state) {
        return getCriteria()
                .add(Restrictions.between("date", from, to))
                .add(Restrictions.eq("state", state))
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public List<Reservation> list(int first, int num) {
        return getCriteria()
                .setFirstResult(first)
                .setMaxResults(num)
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public List<Reservation> list() {
        return getCriteria().addOrder(Order.desc("id")).list();
    }


    private Criteria getCriteria() {
        return getSession().createCriteria(Reservation.class);
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
