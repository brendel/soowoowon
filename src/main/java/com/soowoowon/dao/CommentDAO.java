package com.soowoowon.dao;

import com.soowoowon.model.Article;
import com.soowoowon.model.Comment;

import java.util.List;

/**
 * {@link com.soowoowon.model.Comment} 도메인 모델에 대한 DAO.CRUD 인터페이스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.Comment
 * @see com.soowoowon.dao.CommentDAOHibernate
 */
public interface CommentDAO {
    void add(Comment comment);

    void update(Comment comment);

    Comment get(int id);

    List<Comment> getListByArticle(Article article);

    List<Comment> getAll();

    void delete(Comment comment);
}
