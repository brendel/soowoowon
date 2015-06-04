package com.soowoowon.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring Security에서 권한이 없는 유저가 특정 페이지를 요청했을 때 던져지는 예외(AccessDeniedException)를 처리하는 클래스.
 * edufarm에 특화되어 있다. 요청한 페이지의 권한이 없을 경우 /403 페이지로 리다이렉트한다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        String servletPath = httpServletRequest.getServletPath();
        if (servletPath.equals("/401") || servletPath.equals("/signup")) {
            httpServletResponse.sendRedirect("/");
            return;
        }
        httpServletResponse.sendRedirect("/403");
    }
}
