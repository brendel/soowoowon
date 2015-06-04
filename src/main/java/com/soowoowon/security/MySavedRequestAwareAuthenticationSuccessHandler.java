package com.soowoowon.security;

import org.json.simple.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 유저가 원래 요청했으나, 유저 인증(authentication)이 없어 응답하지 못했던 URL을 기억해 뒀다가,
 * 인증이 성공했을 때 그 URL를 유저에게 보낸다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
public class MySavedRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws ServletException, IOException {
        final SavedRequest savedRequest = requestCache.getRequest(request, response);

        response.setContentType("application/json; charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        PrintWriter printout = response.getWriter();

        if (savedRequest == null || savedRequest.getRedirectUrl().isEmpty()) {
            clearAuthenticationAttributes(request);
            String referer = request.getHeader("Referer");
            if (referer.isEmpty())
                referer = "/";
            referer = referer.split("\\?")[0];
            jsonObject.put("redirect", referer);
            printout.write(jsonObject.toJSONString());
            printout.flush();
            return;
        }

        final String redirectUrl = savedRequest.getRedirectUrl();
        clearAuthenticationAttributes(request);
        jsonObject.put("redirect", redirectUrl);
        printout.write(jsonObject.toJSONString());
        printout.flush();
        return;
    }

    public void setRequestCache(final RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}