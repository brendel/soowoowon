package edufarm.dao;

import edufarm.model.Article;
import edufarm.model.User;

import java.util.Date;
import java.util.List;

/**
 * {@link edufarm.model.Article} 도메인 모델에 대한 DAO.CRUD 인터페이스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.Article
 * @see edufarm.dao.ArticleDAOHibernate
 */
public interface ArticleDAO {

    Article get(int id);

    Article getNewest();

    void add(Article article);

    void update(Article article);

    void delete(Article article);

    int count();

    List<Article> list();

    List<Article> list(User user);

    List<Article> list(Date date);

    List<Article> list(int first, int num);

}
