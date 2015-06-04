package com.soowoowon.controller;

import com.soowoowon.exception.ResourceNotFoundException;
import com.soowoowon.model.Article;
import com.soowoowon.model.Reservation;
import com.soowoowon.model.User;
import com.soowoowon.service.ArticleService;
import com.soowoowon.service.ReservationService;
import com.soowoowon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * 예약/문의(Reservation) 콘트롤러.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
@Controller
@RequestMapping("/reservation")
public class ReservationController {
    private static final int perPage = 20;

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    /**
     * 예약/문의(reservation) 내역 리스트 첫 페이지. /reservation
     * <p>/reservation/1 과 동일.
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        return list(1, model);
    }

    /**
     * 예약/문의(reservation) 내역 리스트. /reservation/{id}
     * <p>페이지 당 {@link com.soowoowon.controller.ReservationController#perPage}에 설정된 갯수만큼 최신순으로 정렬 된 리스트를 뷰로 넘긴다.
     *
     * @param page
     * @param model
     * @return
     */
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String list(@PathVariable int page, Model model) {
        int count = reservationService.count();
        int lastPage = (count / perPage) + ((count % perPage) == 0 ? 0 : 1);

        String[] stateText = {"취소 됨", "검토 중", "답변 완료", "예약 확정"};
        String[] stateCss = {"label-default", "label-info", "label-primary", "label-success"};

        model.addAttribute("stateCss", stateCss);
        model.addAttribute("stateText", stateText);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("currPage", page);
        model.addAttribute("reservationList", reservationService.list(--page * perPage, perPage));
        Article article = articleService.getNewest();
        if (article != null && article.getContent().length() < 6) {
            article = null;
        }
        model.addAttribute("article", article);
        return "reservation.list";
    }

    /**
     * 예약/문의하기 페이지. /reservation/new
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newReservation(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation.new";
    }

    /**
     * 예약/문의 내역 업데이트 페이지. /reservation/update
     * <p>작성자와 USER_ADMIN 권한을 가진 유저만 조회 가능.
     *
     * @param id
     * @param model
     * @param principal
     * @return
     * @throws AccessDeniedException
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model, Principal principal) throws AccessDeniedException {
        Reservation reservation = reservationService.get(id);
        User user = userService.get(principal.getName());
        if (user.getRole() < 9 && !reservation.getUser().getEmail().equals(user.getEmail())) {
            throw new AccessDeniedException("글쓴이 본인만 수정할 수 있습니다.");
        }
        reservation.setContent(reservation.getContent().replaceAll("#nl", "\n"));
        model.addAttribute("reservation", reservation);
        return "reservation.update";
    }

    /**
     * 개별 예약/문의(reservation) 내역 조회. /reservation/detail/{id}
     * <p>작성자 본인과 USER_ADMIN 권한을 가진 유저만 조회 가능.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable int id, Model model) {
        Reservation reservation = reservationService.get(id);
        reservation.setContent(reservation.getContent().replaceAll("#nl", "<br>"));
        String part;
        if (reservation.getPart() == 1)
            part = "1부 (11:00-15:00)";
        else
            part = "2부 (14:00-17:00)";
        String[] stateText = {"취소 됨", "검토 중", "답변 완료", "예약 확정"};
        String[] stateCss = {"btn-default", "btn-info", "btn-primary", "btn-success"};
        model.addAttribute("stateText", stateText);
        model.addAttribute("stateCss", stateCss);
        model.addAttribute("reservation", reservation);
        model.addAttribute(("part"), part);
        return "reservation.detail";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException() {
        return "error404";
    }
}