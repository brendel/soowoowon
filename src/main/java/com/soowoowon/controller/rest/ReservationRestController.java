package com.soowoowon.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.soowoowon.json.Writer;
import com.soowoowon.model.Comment;
import com.soowoowon.model.Message;
import com.soowoowon.model.Reservation;
import com.soowoowon.model.User;
import com.soowoowon.service.ReservationService;
import com.soowoowon.service.SmsService;
import com.soowoowon.service.UserService;
import com.soowoowon.service.ValidationResponseService;
import com.soowoowon.viewModel.ReservationResponse;
import com.soowoowon.viewModel.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;

// TODO: Refactor com.soowoowon.service.ValidationResponseService into ViewModels

/**
 * 예약/문의({@link com.soowoowon.model.Reservation}) REST API. /api/reservation
 * <p>스프링 시큐리티 설정에 의해 ROLE_USER 권한 이상만 사용가능.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
@Controller
@RequestMapping("/api/reservation")
public class ReservationRestController {

    private static final int perPage = 5;

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @Autowired
    SmsService smsService;

    @Autowired
    ValidationResponseService validationResponseService;

    /**
     * Get List of Reservations
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String getReservations(@PathVariable int page) throws JsonProcessingException {
        List<Reservation> reservations = reservationService.list((page - 1) * perPage, perPage);
        return Writer.reservationListWriter.writeValueAsString(reservations);
    }

    /**
     * 새로운 예약({@link com.soowoowon.model.Reservation}).
     * <p>JSON String으로 인코딩 된 {@link com.soowoowon.model.Reservation}을 받는다(HTTP POST).
     *
     * @param reservation
     * @param result
     * @param principal
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = {"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public ValidationResponse submitReservation(@Valid @RequestBody Reservation reservation, BindingResult result, Principal principal) throws IOException, NoSuchAlgorithmException {
        ValidationResponse validationResponse = new ValidationResponse();
        if (result.hasErrors()) {
            return validationResponseService.setResponseFailed(validationResponse, result);
        }
        User user = userService.get(principal.getName());
        reservation.setUser(user);
        reservationService.add(reservation);
        validationResponse.setStatus("SUCCESS");

        // Notify Admin.
        Message sms = new Message();
        sms.setMessage("[수우원 에듀팜] http://edu.com.soowoowon.com/reservation/detail/" + reservation.getId());
        smsService.sendToUser(sms, userService.get(1));

        return validationResponse;
    }

    /**
     * Get a Reservation
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public ReservationResponse getDetail(@PathVariable int id) {
        // TODO Exception Handling
        return reservationService.getResponseForUser(id);
    }

    /**
     * 예약({@link com.soowoowon.model.Reservation})의 변경.
     * <p>JSON String으로 인코딩 된 {@link com.soowoowon.model.Reservation}을 받는다(HTTP POST).
     *
     * @param id
     * @param modifiedReservation
     * @param result
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = {"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public ValidationResponse updateReservation(@PathVariable int id, @Valid @RequestBody Reservation modifiedReservation, BindingResult result) {
        ValidationResponse validationResponse = new ValidationResponse();
        if (result.hasErrors()) {
            return validationResponseService.setResponseFailed(validationResponse, result);
        }
        Reservation reservation = reservationService.get(id);
        reservation.setTitle(modifiedReservation.getTitle());
        reservation.setContent(modifiedReservation.getContent());
        reservation.setDate(modifiedReservation.getDate());
        reservation.setPart(modifiedReservation.getPart());
        reservation.setAdult(modifiedReservation.getAdult());
        reservation.setChild(modifiedReservation.getChild());

        reservationService.update(reservation);
        validationResponse.setStatus("SUCCESS");
        return validationResponse;
    }

    /**
     * 예약({@link com.soowoowon.model.Reservation})의 삭제.
     * <p>HTTP DELETE 또는 HTTP POST의 숨겨진 필드(_method: "DELETE")로 접근 가능.
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteReservation(@PathVariable int id) {
        Reservation reservation = reservationService.get(id);
        reservationService.delete(reservation);
    }

    /**
     * 예약({@link com.soowoowon.model.Reservation})의 상태({@link com.soowoowon.model.Reservation#articleState}) 변경.
     *
     * @param id
     * @param state
     */
    @RequestMapping(value = "/{id}/state/{state}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateState(@PathVariable int id, @PathVariable String state) throws IOException, NoSuchAlgorithmException {
        reservationService.changeState(id, state);
    }

    /**
     * 예약({@link com.soowoowon.model.Reservation})에 댓글({@link com.soowoowon.model.Comment}) 추가.
     * <p>JSON String으로 인코딩 된 {@link com.soowoowon.model.Comment}를 받는다(HTTP POST).
     *
     * @param id
     * @param comment
     * @param result
     * @param principal
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, params = {"content"})
    public String submitcomment(Model model, @PathVariable int id, @Valid Comment comment, BindingResult result, Principal principal) throws IOException, NoSuchAlgorithmException {
        if (result.hasErrors()) {
            throw new ValidationException();
        }
        User user = userService.get(principal.getName());
        Reservation reservation = reservationService.get(id);
        comment.setUser(user);
        comment.setArticle(reservation);
        reservationService.addComment(comment);
        model.addAttribute("comment", comment);

        // if comment writer is not an admin user, send sms to admin.
        if (user.getRole() < 9) {
            Message sms = new Message();
            sms.setMessage("[수우원 에듀팜] http://edu.soowoowon.com/reservation/detail/" + id);
            smsService.sendToUser(sms, userService.get(1));
            return "newComment";
        }
        // else send sms to reservation user. 2013-06-29 Kiwon Lee.
        Message sms = new Message();
        sms.setMessage("[수우원 에듀팜] #name#님의 예약/문의에 대한 답변이 있습니다");
        smsService.sendToUser(sms, reservation.getUser());
        return "newComment";
    }

    /**
     * 예약({@link com.soowoowon.model.Reservation})의 댓글({@link com.soowoowon.model.Comment} 삭제.
     * <p>HTTP DELETE 또는 HTTP POST의 숨겨진 필드(_method: "DELETE")로 접근 가능.
     *
     * @param commentId
     */
    @RequestMapping(value = "/{reservationId}/{commentId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletecomment(@PathVariable int commentId) {
        Comment comment = reservationService.getComment(commentId);
        reservationService.deleteComment(comment);
    }
}
