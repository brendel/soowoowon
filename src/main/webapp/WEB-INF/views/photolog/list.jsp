<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="article-hero" class="edufarm-hero">
    <div class="container">
        <div class="jumbotron">
            <h1>즐거운 시간 보내셨나요?</h1>

            <h3>수우원 교육농장 프로그램 후기와 사진을 공유해주세요.</h3>

            <p><a href="/article/new" class="btn btn-primary btn-lg" role="button">후기 올리기</a></p>
        </div>
    </div>
</div>
<div id="main" class="container">
    <div class="row">
        <div class="col-md-9 col-sm12 col-xs-12">
            <div id="reservation-list" class="list-group">
                <c:forEach var="article" items="${articleList}">
                    <a href="/article/detail/${article.id}"
                       class="list-group-item">
                        <c:if test="${article.commentCount != 0}"><span
                                class="badge">${article.commentCount}</span></c:if>
                        <h4 class="list-group-item-heading">
                                ${article.title}
                        </h4>

                        <p class="list-group-item-text">
                            <small>${article.user.displayName} | <fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                                                                                 value="${article.published}"/></small>
                        </p>
                    </a>
                </c:forEach>
            </div>
            <div class="row text-center">
                <ul class="pagination">
                    <li class="<c:if test='${currPage == 1}'>disabled</c:if>"><a
                            href="<c:if test='${currPage != 1}'>/article/${currPage - 1}</c:if>">&laquo;</a></li>
                    <c:forEach var="i" begin="1" end="${lastPage}">
                        <li class="<c:if test='${currPage == i}' >active</c:if>"><a href="/article/${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <li class="<c:if test='${currPage == lastPage}'>disabled</c:if>"><a
                            href="<c:if test='${currPage != lastPage}'>/article/${currPage + 1}</c:if>">&raquo;</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>