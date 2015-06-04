package com.soowoowon.controller;

import com.soowoowon.excelView.ReservationExcelView;
import com.soowoowon.model.Article;
import com.soowoowon.model.User;
import com.soowoowon.service.*;
import com.soowoowon.viewModel.SearchForm;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

/**
 * 관리자 컨트롤러. /admin/**
 * 스프링 시큐리티 설정에 의해 USER_ADMIN 권한 이상만 접근 가능.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    StatService statService;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    SmsService smsService;

    /**
     * 관리자 영역의 첫 페이지.
     * <p>{@link com.soowoowon.service.StatService}에서 방문자 IP, OS, 웹브라우저, 리퍼러 통계등을 가져와서 뷰에 넘겨준다.
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String statPage(Model model) {
        Date today = new Date();
        Date aWeekAgo = DateUtils.addWeeks(today, -1);
        Date aMonthAgo = DateUtils.addMonths(today, -1);


        model.addAttribute("osStat", statService.getOsStat(aMonthAgo, today));
        model.addAttribute("browserStat", statService.getWebBrowserStat(aMonthAgo, today));
        model.addAttribute("referrerStat", statService.getReferrerStat(aMonthAgo, today));
        model.addAttribute(("monthTotal"), statService.getCount(aMonthAgo, today));

        model.addAttribute("dateStat", statService.getDateStat(aWeekAgo, today));
        model.addAttribute("visits", statService.get(aWeekAgo, today));
        model.addAttribute(("weekTotal"), statService.getCount(aWeekAgo, today));

        return "admin.stat";
    }

    /**
     * 전체 회원정보 페이지.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userPage(Model model) {
        model.addAttribute("userList", userService.list());
        return "admin.users";
    }

    /**
     * 사이트 첫 페이지 수정 페이지.
     * <p>사이트 첫 페이지는 가장 최신의 {@link com.soowoowon.model.Article}이다.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/editHome", method = RequestMethod.GET)
    public String editHomePage(Model model) {
        Article article = articleService.getNewest();
        if (article != null) {
            model.addAttribute("article", article);
        }

        return "admin.editHome";
    }

    /**
     * 수정된 form을 ARTICLE Request로 받는다.
     *
     * @param article
     * @param result
     * @param model
     * @param principal
     * @return
     */
    @RequestMapping(value = "/editHome", method = RequestMethod.POST, params = {"title", "content"})
    public String submitEditHome(@Valid Article article, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            return "admin.editHome";
        }

        User user = userService.get(principal.getName());
        article.setUser(user);
        article.setPublished(new Date());

        articleService.add(article);

        model.addAttribute("article", article);

        return "admin.editHome";
    }

    /**
     * 예약/문의 조회 페이지.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/reservation", method = RequestMethod.GET)
    public String reservationPage(Model model) {
        return "admin.reservation";
    }

    /**
     * 예약/문의 검색 REST API.
     * <p>{@link SearchForm}에 바인딩된 검색 조건에 따라 예약/문의 내역을 검색해서 응답한다.
     *
     * @param query
     * @param result
     * @param model
     * @return
     */
    @RequestMapping(value = "/reservation/search", method = RequestMethod.POST, consumes = {"application/json; charset=UTF-8"})
    public String reservationSearchAjax(@Valid @RequestBody SearchForm query, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "reservationSearchResult";
        }
        String[] stateText = {"취소 됨", "검토 중", "답변 완료", "예약 확정"};
        model.addAttribute("reservationList", reservationService.list(query));
        model.addAttribute("stateText", stateText);
        return "reservationSearchResult";
    }

    /**
     * 예약/문의 검색 REST API. {@link ReservationExcelView}를 리턴한다.
     * <p>대부분의 브라우저에서는 엑셀 파일로 다운로드 되는데, 사파리 계열에서는 페이지로 열리기 때문에 페이지를 직접 저장해줘야 한다.
     *
     * @param query
     * @param result
     * @param model
     * @return
     * @see com.soowoowon.excelView.ReservationExcelView
     */
    @RequestMapping(value = "/reservation/search.xls", method = RequestMethod.POST)
    public View reservationSearchXml(@Valid SearchForm query, BindingResult result, Model model) {

        String[] stateText = {"취소 됨", "검토 중", "답변 완료", "예약 확정"};
        model.addAttribute("reservationList", reservationService.list(query));
        model.addAttribute("stateText", stateText);
        return new ReservationExcelView();
    }

    /**
     * 문자 전송 내역 페이지.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/sms", method = RequestMethod.GET)
    public String smsRecordPage(Model model) {
        model.addAttribute("smsList", smsService.list(30));
        return "admin.sms";
    }
}