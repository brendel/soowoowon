package com.soowoowon.dao;

import com.soowoowon.model.Article;
import com.soowoowon.model.Comment;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link com.soowoowon.dao.CommentDAO}의 Hibernate 구현.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.Comment
 * @see com.soowoowon.dao.CommentDAO
 */
@Repository
public class CommentDAOHibernate implements CommentDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(Comment comment) {
        Session session = getSession();
        session.save(comment);
        session.flush();
    }

    @Override
    public void update(Comment comment) {
        getSession().update(comment);
    }

    @Override
    public Comment get(int id) {
        return (Comment) getCriteria()
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public List<Comment> getListByArticle(Article article) {
        return getCriteria()
                .add(Restrictions.eq("article", article))
                .addOrder(Order.desc("id"))
                .list();
    }

    @Override
    public List<Comment> getAll() {
        return getCriteria().addOrder(Order.desc("id")).list();
    }

    @Override
    public void delete(Comment comment) {
        getSession().delete(comment);
    }

    private Criteria getCriteria() {
        return getSession().createCriteria(Comment.class);
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
