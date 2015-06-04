package com.soowoowon.controller;

import com.soowoowon.exception.ResourceNotFoundException;
import com.soowoowon.model.Article;
import com.soowoowon.model.User;
import com.soowoowon.service.ArticleService;
import com.soowoowon.service.UserService;
import com.soowoowon.service.ValidationResponseService;
import com.soowoowon.viewModel.UploadedPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

/**
 * 사진 후기(Article) 콘트롤러. /article/**
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.Article
 * @see com.soowoowon.service.ArticleService
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    private static final int perPage = 20;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    ValidationResponseService validationResponseService;

    /**
     * 첫 번째 페이지. /article
     * <p>/article/1 과 동일.
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        return list(1, model);
    }

    /**
     * 사진후기(article) 리스트. /article({id}
     * <p>페이지 당 {@link com.soowoowon.controller.ArticleController#perPage}에 설정된 갯수만큼 최신순으로 정렬 된 리스트를 뷰로 넘긴다..
     *
     * @param page
     * @param model
     * @return
     */
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String list(@PathVariable int page, Model model) {
        int count = articleService.count();
        int lastPage = (count / perPage) + ((count % perPage) == 0 ? 0 : 1);

        model.addAttribute("lastPage", lastPage);
        model.addAttribute("currPage", page);
        List<Article> articleList = articleService.list(--page * perPage, perPage);
        model.addAttribute("articleList", articleList);
        return "article.list";
    }

    /**
     * 사진후기(article) 작성 페이지. /article/new
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newArticlePage(Model model) {
        model.addAttribute("uploadedPhoto", new UploadedPhoto());
        return "article.new";
    }

    /**
     * 사진후기(article) 편집 페이지. /article/update
     * <p>작성자와 USER_ADMIN 권한을 가진 멤버만 조회 가능.
     *
     * @param id
     * @param model
     * @param principal
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updatePage(@PathVariable int id, Model model, Principal principal) {
        Article article = articleService.get(id);
        User user = userService.get(principal.getName());
        if (user.getRole() < 9 && !article.getUser().getEmail().equals(user.getEmail())) {
            throw new AccessDeniedException("글쓴이 본인만 수정할 수 있습니다.");
        }
        article.setContent(article.getContent().replaceAll("#nl", "\n"));
        model.addAttribute("article", article);
        return "article.update";
    }

    /**
     * 개별 사진후기(article) 보기. /article/detail
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailPage(@PathVariable int id, Model model) {
        Article article = articleService.get(id);
        article.setContent(article.getContent().replaceAll("#nl", "<br>"));
        model.addAttribute("article", article);
        return "article.detail";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException() {
        return "error404";
    }
}
