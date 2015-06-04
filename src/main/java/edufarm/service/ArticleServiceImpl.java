package edufarm.service;

import edufarm.dao.ArticleDAO;
import edufarm.dao.CommentDAO;
import edufarm.dao.PhotoDAO;
import edufarm.exception.ResourceNotFoundException;
import edufarm.model.Article;
import edufarm.model.Comment;
import edufarm.model.Photo;
import edufarm.model.User;
import edufarm.viewModel.ArticleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * {@link ArticleService}의 구현.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.Photo
 * @see edufarm.service.PhotoService
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDAO articleDAO;

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    PhotoDAO photoDAO;

    @Autowired
    SanitizerService sanitizerService;

    @Autowired
    PhotoService photoService;

    @Override
    public Article get(int id) {
        Article article = articleDAO.get(id);
        if (article == null) {
            throw new ResourceNotFoundException("Article not found.", id);
        }
        return article;
    }

    @Override
    public Article getNewest() {
        return articleDAO.getNewest();
    }

    @Override
    public void add(Article article) {
        sanitizerService.clean(article);
        article.setPublished(new Date());
        articleDAO.add(article);
    }

    @Override
    public void add(Article article, ArticleForm articleForm) {
        article.setTitle(articleForm.getTitle());
        article.setContent(articleForm.getContent());
        add(article);

        List<Integer> photoId = articleForm.getUploadedPhotos();
        if (photoId != null && !photoId.isEmpty()) {
            Photo photo;
            for (int id : photoId) {
                photo = photoDAO.get(id);
                photo.setArticle(article);
                photoDAO.update(photo);
            }
        }
    }

    @Override
    public void update(Article article) {
        sanitizerService.clean(article);
        articleDAO.update(article);
    }

    @Override
    public void update(Article article, ArticleForm articleForm) {
        article.setTitle(articleForm.getTitle());
        article.setContent(articleForm.getContent());
        update(article);

        List<Integer> photoId = articleForm.getUploadedPhotos();
        if (!photoId.isEmpty()) {
            Photo photo;
            for (int id : photoId) {
                photo = photoDAO.get(id);
                photo.setArticle(article);
                photoDAO.update(photo);
            }
        }

    }

    @Override
    public void delete(Article article) {
        Set<Photo> photos = article.getPhotos();
        if (!photos.isEmpty()) {
            for (Photo photo : photos) {
                photoService.deletePhoto(photo);
            }
        }
        articleDAO.delete(article);
    }

    @Override
    public Comment getComment(int id) {
        Comment comment = commentDAO.get(id);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found.", id);
        }
        return comment;
    }

    @Override
    public void addComment(Comment comment) {
        sanitizerService.clean(comment);
        comment.setPublished(new Date());
        Article article = comment.getArticle();
        int commentCount = article.getCommentCount();
        article.setCommentCount(++commentCount);

        commentDAO.add(comment);
        articleDAO.update(article);
    }

    @Override
    public void deleteComment(Comment comment) {
        Article article = comment.getArticle();
        int commentCount = article.getCommentCount();
        article.setCommentCount(--commentCount);

        commentDAO.delete(comment);
        articleDAO.update(article);
    }

    @Override
    public int count() {
        return articleDAO.count();
    }

    @Override
    public List<Article> list(User user) {
        return articleDAO.list(user);
    }

    @Override
    public List<Article> list(Date date) {
        return articleDAO.list(date);
    }

    @Override
    public List<Article> list(int first, int num) {
        return articleDAO.list(first, num);
    }

    @Override
    public List<Article> list() {
        return articleDAO.list();
    }
}
