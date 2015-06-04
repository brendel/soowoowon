package edufarm.dao;

import edufarm.model.SiteStat;

import java.util.Date;
import java.util.List;

/**
 * {@link edufarm.model.SiteStat}의 DAO.CRUD 인터페이스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.SiteStat
 * @see edufarm.dao.SiteStatDAOHibernate
 */
public interface SiteStatDAO {
    void add(SiteStat stat);

    void update(SiteStat stat);

    void delete(SiteStat stat);

    int count(Date from, Date to);

    List<SiteStat> get(Date from, Date to);

    List getByDistinctDate(Date from, Date to);

    List getByDistinctReferrer(Date from, Date to);

    List getByDistinctUserWebBrowser(Date from, Date to);

    List getByDistinctUserOS(Date from, Date to);
}
