package edufarm.dao;

import edufarm.model.Article;
import edufarm.model.User;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * {@link ArticleDAO}의 Hibernate 구현.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.Article
 * @see ArticleDAO
 */
@Repository
public class ArticleDAOHibernate implements ArticleDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Article get(int id) {
        return (Article) getCriteria().add(Restrictions.eq("id", id))
                .setFetchMode("comments", FetchMode.JOIN)
                .setFetchMode("photos", FetchMode.JOIN)
                .uniqueResult();
    }

    @Override
    public Article getNewest() {
        return (Article) getCriteria().addOrder(Order.desc("id"))
                .setMaxResults(1)
                .setFetchMode("comments", FetchMode.JOIN)
                .setFetchMode("photos", FetchMode.JOIN)
                .uniqueResult();
    }

    @Override
    public void add(Article article) {
        getSession().save(article);
    }

    @Override
    public void update(Article article) {
        getSession().update(article);
    }

    @Override
    public void delete(Article article) {
        getSession().delete(article);
    }

    @Override
    public int count() {
        return ((Long) getCriteria().setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List<Article> list() {
        return getCriteria().addOrder(Order.desc("id")).list();
    }

    @Override
    public List<Article> list(Date date) {
        return getCriteria()
                .add(Restrictions.eq("published", date))
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public List<Article> list(User user) {
        return getCriteria()
                .add(Restrictions.eq("user", user))
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public List<Article> list(int first, int num) {
        return getCriteria()
                .setFirstResult(first)
                .setMaxResults(num)
                .addOrder(Order.desc("id"))
                .list();
    }

    private Criteria getCriteria() {
        return getSession().createCriteria(Article.class);
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
