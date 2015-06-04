package edufarm.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import edufarm.model.Article;
import edufarm.model.Comment;
import edufarm.model.User;
import edufarm.service.ArticleService;
import edufarm.service.UserService;
import edufarm.service.ValidationResponseService;
import edufarm.viewModel.ArticleForm;
import edufarm.viewModel.ValidationResponse;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.security.Principal;

/**
 * 사진후기({@link edufarm.model.Article}) REST API. /api/article
 * <p>스프링 시큐리티 설정에 의해 ROLE_USER 권한 이상만 사용가능.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
@Controller
@RequestMapping("/api/article")
public class ArticleRestController {
    @Autowired
    ValidationResponseService validationResponseService;

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    /**
     * 새로운 사진후기({@link edufarm.model.Article}).
     * <p>JSON String으로 인코딩 된 {@link ArticleForm}을 받는다(HTTP POST).
     *
     * @param articleForm
     * @param result
     * @param principal
     * @return
     * @throws ParseException
     */
    @RequestMapping(method = RequestMethod.POST, consumes = {"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public ValidationResponse newArticle(@Valid @RequestBody ArticleForm articleForm, BindingResult result, Principal principal) throws ParseException {
        ValidationResponse validationResponse = new ValidationResponse();
        if (result.hasErrors()) {
            return validationResponseService.setResponseFailed(validationResponse, result);
        }
        User user = userService.get(principal.getName());
        Article article = new Article();
        article.setUser(user);

        articleService.add(article, articleForm);
        validationResponse.setStatus("SUCCESS");
        return validationResponse;
    }

    /**
     * 사진후기({@link edufarm.model.Article}) 업데이트.
     * <p>JSON String으로 인코딩 된 {@link ArticleForm}을 받는다(HTTP POST).
     *
     * @param id
     * @param articleForm
     * @param result
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = {"application/json; charset=UTF-8"}, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public ValidationResponse updateArticle(@PathVariable int id, @Valid @RequestBody ArticleForm articleForm, BindingResult result) throws ParseException {
        ValidationResponse validationResponse = new ValidationResponse();
        if (result.hasErrors()) {
            return validationResponseService.setResponseFailed(validationResponse, result);
        }
        Article article = articleService.get(id);

        articleService.update(article, articleForm);
        validationResponse.setStatus("SUCCESS");
        return validationResponse;
    }

    /**
     * 사진후기({@link edufarm.model.Article}) 삭제.
     * <p>HTTP DELETE 또는 HTTP POST 요청의 숨겨진 필드(_method: "DELETE")로 접근 가능.
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteArticle(@PathVariable int id) {
        Article article = articleService.get(id);
        articleService.delete(article);
    }

    /**
     * 사진후기({@link edufarm.model.Article})의 댓글({@link edufarm.model.Comment} 추가.
     * <p>JSON String으로 인코딩 된 {@link edufarm.model.Comment}를 받는다(HTTP POST).
     *
     * @param model
     * @param id
     * @param comment
     * @param result
     * @param principal
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, params = {"content"})
    public String submitReply(Model model, @PathVariable int id, @Valid Comment comment, BindingResult result, Principal principal) throws JsonProcessingException {
        if (result.hasErrors())
            throw new ValidationException();
        User user = userService.get(principal.getName());
        comment.setUser(user);
        comment.setArticle(articleService.get(id));
        articleService.addComment(comment);
        model.addAttribute("comment", comment);
        return "newComment";
    }

    /**
     * 사진후기({@link edufarm.model.Article})의 댓글({@link edufarm.model.Comment} 삭제.
     * <p>HTTP DELETE 또는 HTTP POST의 숨겨진 필드(_method: "DELETE")로 접근 가능.
     *
     * @param commentId
     */
    @RequestMapping(value = "/{articleId}/{commentId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable int commentId) {
        Comment comment = articleService.getComment(commentId);
        articleService.deleteComment(comment);
    }
}
