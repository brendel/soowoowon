package edufarm.dao;

import edufarm.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link edufarm.dao.UserDAO}의 Hibernate 구현.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.User
 * @see edufarm.dao.UserDAO
 */
@Repository
public class UserDAOHibernate implements UserDAO {

    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        getSession().save(user);
    }

    @Override
    public void update(User user) {
        getSession().update(user);
    }

    @Override
    public User get(int id) {
        return (User) getCriteria()
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public User get(String email) {
        return (User) getCriteria()
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public void delete(User user) {
        getSession().delete(user);
    }

    @Override
    public List<User> getAll() {
        return getCriteria().addOrder(Order.desc("id")).list();
    }

    @Override
    public int count(String email) {
        int count = ((Long) getCriteria().add(Restrictions.eq("email", email))
                .setProjection(Projections.rowCount()).uniqueResult()).intValue();
        return count;
    }

    private Criteria getCriteria() {
        return getSession().createCriteria(User.class);
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
