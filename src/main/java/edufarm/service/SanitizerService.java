package edufarm.service;

import edufarm.model.Article;
import edufarm.model.Comment;

/**
 * 클라이언트로부터 받은 폼(form) 데이터에서 XSS, SQL Injection 등
 * 유해한 요소를 모두 제거
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.service.SanitizerServiceImpl
 */
public interface SanitizerService {
    void clean(Article article);

    void clean(Comment comment);
}
