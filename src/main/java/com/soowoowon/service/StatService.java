package com.soowoowon.service;

import com.soowoowon.model.SiteStat;

import java.util.Date;
import java.util.List;

/**
 * {@link com.soowoowon.model.SiteStat}의 서비스 인터페이스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.SiteStat
 * @see com.soowoowon.service.StatServiceImpl
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
