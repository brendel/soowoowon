package com.soowoowon.controller;

import com.soowoowon.model.Article;
import com.soowoowon.model.User;
import com.soowoowon.service.ArticleService;
import com.soowoowon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 홈 컨트롤러.
 * <p>첫 페이지, 회원가입 페이지, 401, 403, 회원 정보 페이지를 처리.
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public String homepage(Model model) {
        Article article = articleService.getNewest();
        if (article != null) {
            model.addAttribute("content", article.getContent());
        }
        return "home";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signupPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "401", method = RequestMethod.GET)
    public String authenticationRequiredPage() {
        return "authenticationRequired";
    }

    @RequestMapping(value = "403", method = RequestMethod.GET)
    public String accessDeniedPage(HttpServletRequest request, Model model) {
        model.addAttribute("url", request.getHeader("Referer"));
        return "error403";
    }

    @RequestMapping(value = "userinfo", method = RequestMethod.GET)
    public String userinfoPage(Model model, Principal principal) {
        User user = userService.get(principal.getName());
        model.addAttribute("user", user);
        return "userinfo";
    }
}