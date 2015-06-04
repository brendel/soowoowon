package edufarm.dao;

import edufarm.model.Message;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link MessageDAO}의 Hibernate 구현.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see Message
 * @see MessageDAO
 */
@Repository
public class MessageDAOHibernate implements MessageDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(Message message) {
        getSession().save(message);
    }

    @Override
    public void update(Message message) {
        getSession().update(message);
    }

    @Override
    public void delete(Message message) {
        getSession().delete(message);
    }

    @Override
    public List<Message> list(String displayName) {
        return getCriteria()
                .add(Restrictions.like("smsReceiverName", displayName, MatchMode.ANYWHERE))
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public List<Message> list() {
        return getCriteria()
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public List<Message> list(int num) {
        return getCriteria()
                .addOrder(Order.desc("id"))
                .setMaxResults(num)
                .list();
    }

    private Criteria getCriteria() {
        return getSession().createCriteria(Message.class);
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
