package com.soowoowon.dao;

import com.soowoowon.model.SiteStat;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
 * {@link com.soowoowon.dao.SiteStatDAO}의 Hibernate 구현.
 * <p>다른 하이버네이트 DAO 구현들과는 다르게 결과값이 리포트인 경우가 많아 HQL이 주로 사용되었다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.SiteStat
 * @see com.soowoowon.dao.SiteStatDAO
 */
@Repository
public class SiteStatDAOHibernate implements SiteStatDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void add(SiteStat stat) {
        getSession().save(stat);
    }

    @Override
    public void update(SiteStat stat) {
        getSession().update(stat);
    }

    @Override
    public void delete(SiteStat stat) {
        getSession().delete(stat);
    }

    @Override
    public List<SiteStat> get(Date from, Date to) {
        List<SiteStat> stats = getCriteria()
                .add(Restrictions.between("visited", from, to))
                .addOrder(Order.desc("visited"))
                .list();
        return stats;
    }

    @Override
    public List getByDistinctDate(Date from, Date to) {
        Query query = getSession().createQuery(
                "select distinct(date(s.visited)) as date, count(*) as visiters from SiteStat as s where s.visited between :from and :to group by date(s.visited) order by date(s.visited) desc"
        );
        return getDistinct(from, to, query);
    }

    @Override
    public List getByDistinctReferrer(Date from, Date to) {
        Query query = getSession().createQuery(
                "select distinct s.referrer as referrer, count(*) as visiters from SiteStat as s where s.visited between :from and :to group by s.referrer order by visiters desc"
        );
        return getDistinct(from, to, query);
    }

    @Override
    public List getByDistinctUserWebBrowser(Date from, Date to) {
        Query query = getSession().createQuery(
                "select distinct s.webBrowser as webBrowser, count(*) as visiters from SiteStat  as s where s.visited between :from and :to group by s.webBrowser order by visiters desc"
        );
        return getDistinct(from, to, query);
    }

    @Override
    public List getByDistinctUserOS(Date from, Date to) {
        Query query = getSession().createQuery(
                "select distinct s.OS as os, count(*) as visiters from SiteStat as s where s.visited between :from and :to group by s.OS order by visiters desc"
        );
        return getDistinct(from, to, query);
    }

    private List getDistinct(Date from, Date to, Query query) {
        query.setMaxResults(7);
        query.setParameter("from", from);
        query.setParameter("to", to);
        return query.list();
    }

    @Override
    public int count(Date from, Date to) {
        return ((Long) getCriteria().add(Restrictions.between("visited", from, to)).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    private Criteria getCriteria() {
        return getSession().createCriteria(SiteStat.class);
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
