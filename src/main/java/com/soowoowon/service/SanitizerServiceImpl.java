package com.soowoowon.service;

import com.soowoowon.model.Article;
import com.soowoowon.model.Comment;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Service;

/**
 * {@link com.soowoowon.service.SanitizerService}의 구현.
 * <p/>
 * 클라이언트로부터 받은 {@link com.soowoowon.model.Article}, {@link com.soowoowon.model.Comment}에서 XSS, SQL Injection 등
 * 유해한 요소를 모두 제거.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 */
@Service
public class SanitizerServiceImpl implements SanitizerService {
    @Override
    public void clean(Article article) {
        String title = article.getTitle();
        title = Jsoup.clean(title, Whitelist.simpleText());
        article.setTitle(title);

        String content = article.getContent();
        content = content.replaceAll("(\r\n|\n)", "#nl");
        content = Jsoup.clean(content, Whitelist.simpleText());
        article.setContent(content);
    }

    @Override
    public void clean(Comment comment) {
        String content = comment.getContent();
        content = Jsoup.clean(content, Whitelist.simpleText());
        comment.setContent(content);
    }
}
