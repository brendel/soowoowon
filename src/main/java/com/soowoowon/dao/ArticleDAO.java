package com.soowoowon.dao;

import com.soowoowon.model.Article;
import com.soowoowon.model.User;

import java.util.Date;
import java.util.List;

/**
 * {@link com.soowoowon.model.Article} 도메인 모델에 대한 DAO.CRUD 인터페이스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.Article
 * @see com.soowoowon.dao.ArticleDAOHibernate
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
