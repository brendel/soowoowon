package com.soowoowon.interceptor;


import com.soowoowon.model.SiteStat;
import com.soowoowon.service.CachedUserAgentStringParser;
import com.soowoowon.service.StatService;
import net.sf.uadetector.OperatingSystem;
import net.sf.uadetector.ReadableUserAgent;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 스프링 프레임워크의 인터셉터(Interceptor). 사이트 방문 통계를 기록하기 위해 특정 페이지로 들어오는 요청을 가로챈다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.service.StatService
 * @see com.soowoowon.model.SiteStat
 */
public class StatLoggerInterceptor implements HandlerInterceptor {

    private static CachedUserAgentStringParser parser = new CachedUserAgentStringParser();

    @Autowired
    StatService statService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        if (!session.isNew()) {
            Date visited = new Date(session.getLastAccessedTime());
            if (DateUtils.isSameDay(visited, new Date())) {
                return true;
            }
        }
        String referrer = request.getHeader("referer");
        if (referrer != null && referrer.contains(request.getServerName()))
            return true;

        SiteStat stat = new SiteStat();
        String unknown = "unknown";

        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        ip = ip != null ? ip : unknown;
        referrer = referrer != null ? referrer : "direct-access";

        stat.setIP(ip);
        stat.setReferrer(referrer);

        if (userAgent.isEmpty()) {
            stat.setWebBrowser(unknown);
            stat.setOS(unknown);
        } else {
            ReadableUserAgent agent = parser.parse(userAgent);
            OperatingSystem os = agent.getOperatingSystem();
            String osName = os.getName();

            if (osName.isEmpty()) {
                osName = unknown;
            }

            String agentName = agent.getName();
            if (agentName.isEmpty()) {
                agentName = unknown;
            } else {
                agentName += " " + agent.getVersionNumber().toVersionString();
            }

            stat.setOS(osName);
            stat.setWebBrowser(agentName);
        }
        stat.setVisited(new Date());

        statService.write(stat);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
