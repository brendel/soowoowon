package edufarm.service;

import edufarm.dao.SiteStatDAO;
import edufarm.model.SiteStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * {@link edufarm.service.StatService}의 구현.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.SiteStat
 * @see edufarm.service.StatService
 */
@Service
@Transactional
public class StatServiceImpl implements StatService {

    @Autowired
    SiteStatDAO siteStatDAO;

    @Override
    public void write(SiteStat stat) {
        siteStatDAO.add(stat);
    }

    @Override
    public List<SiteStat> get(Date from, Date to) {
        return siteStatDAO.get(from, to);
    }

    @Override
    public List getDateStat(Date from, Date to) {
        return siteStatDAO.getByDistinctDate(from, to);
    }

    @Override
    public List getReferrerStat(Date from, Date to) {
        return siteStatDAO.getByDistinctReferrer(from, to);
    }

    @Override
    public List getOsStat(Date from, Date to) {
        return siteStatDAO.getByDistinctUserOS(from, to);
    }

    @Override
    public List getWebBrowserStat(Date from, Date to) {
        return siteStatDAO.getByDistinctUserWebBrowser(from, to);
    }

    @Override
    public int getCount(Date from, Date to) {
        return siteStatDAO.count(from, to);
    }
}
