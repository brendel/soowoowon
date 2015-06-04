package edufarm.service;

import edufarm.model.Article;
import edufarm.model.Comment;
import edufarm.model.User;
import edufarm.viewModel.ArticleForm;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Date;
import java.util.List;

/**
 * {@link edufarm.model.Article}의 서비스 인터페이스(for article).
 * <p>prepost 애노테이션이 붙어있는 메소드들은 특정 권한이 있어야만 사용가능하다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.Article
 * @see edufarm.service.ArticleServiceImpl
 */
public interface ArticleService {

    Article get(int id);

    Article getNewest();

    @PreAuthorize("hasRole('ROLE_USER')")
    void add(Article article);

    @PreAuthorize("hasRole('ROLE_USER')")
    void add(Article article, ArticleForm articleForm);

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #article.user.email")
    void update(Article article);

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #article.user.email")
    void update(Article article, ArticleForm articleForm);

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #article.user.email")
    void delete(Article article);

    Comment getComment(int id);

    @PreAuthorize("hasRole('ROLE_USER')")
    void addComment(Comment comment);

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #comment.user.email")
    void deleteComment(Comment comment);

    int count();

    List<Article> list(User user);

    List<Article> list(Date date);

    List<Article> list(int first, int num);

    List<Article> list();
}
