package edufarm.dao;

import edufarm.model.Photo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link edufarm.dao.PhotoDAO}의 Hibernate 구현.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.Photo
 * @see edufarm.dao.PhotoDAO
 */
@Repository
public class PhotoDAOHibernate implements PhotoDAO {

    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory sessionFactory;

    @Override
    public Photo get(int id) {
        return (Photo) getCriteria()
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public void add(Photo photo) {
        getSession().save(photo);
    }

    @Override
    public void update(Photo photo) {
        getSession().update(photo);
    }

    @Override
    public void delete(Photo photo) {
        getSession().delete(photo);
    }

    @Override
    public List<Photo> list() {
        return getCriteria().addOrder(Order.desc("published")).list();
    }

    private Criteria getCriteria() {
        return getSession().createCriteria(Photo.class);
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
