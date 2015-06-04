package edufarm.service;

import edufarm.model.SiteStat;

import java.util.Date;
import java.util.List;

/**
 * {@link edufarm.model.SiteStat}의 서비스 인터페이스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.SiteStat
 * @see edufarm.service.StatServiceImpl
 */
public interface StatService {
    void write(SiteStat stat);

    List<SiteStat> get(Date from, Date to);

    List getDateStat(Date from, Date to);

    List getReferrerStat(Date from, Date to);

    List getOsStat(Date from, Date to);

    List getWebBrowserStat(Date from, Date to);

    int getCount(Date from, Date to);
}
