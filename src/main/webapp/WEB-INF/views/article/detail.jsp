<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="main" class="container">
    <div class="page-header">
        <h2>후기 게시판</h2>
    </div>
    <div class="row">
        <div class="col-md-9 col-sm12 col-xs-12">
            <div class="row">
                <div class="col-md-12 col-sm12 col-xs-12">
                    <div id="article-detail-panel" class="panel panel-default">
                        <div class="panel-heading">
                            <h4>${article.title}</h4>

                            <p>${article.user.displayName} | <fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                                                                             value="${article.published}"/><sec:authorize
                                    access="hasRole('ROLE_ADMIN') or authentication.name == #article.user.email">
                                <small><a href="/article/update/${article.id}">수정</a> | <a href="#"
                                                                                           data-toggle="modal"
                                                                                           data-target="#article-del-modal">삭제</a>
                                </small>
                            </sec:authorize></p>
                        </div>
                        <div class="panel-body">
                            <div id="detail-content" class="row">
                                <div id="article-photos" class="col-md-12 col-sm-12 col-xs-12">
                                    <c:forEach var="photo" items="${article.photos}">
                                        <a href="${photo.uri}" title="${article.title}">
                                            <img class="img-responsive article-img"
                                                 src="${photo.uri}"
                                                 alt="${photo.fileName}"/>
                                        </a>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <c:out value="${article.content}" escapeXml="false"/>
                            </div>
                        </div>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <div class="panel-footer">
                                <div class="row">
                                    <div class="col-md-4 col-sm-4 col-xs-12">
                                        <span class="glyphicon glyphicon-envelope"></span> <a
                                            href="mailto:${article.user.email}">${article.user.email}</a>
                                    </div>
                                    <div class="col-md-4 col-sm-4 col-xs-12">
                                        <span class="glyphicon glyphicon-earphone"></span> <a
                                            href="tel:${article.user.phone}">${article.user.phone}</a>
                                    </div>
                                </div>
                            </div>
                        </sec:authorize>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 col-sm12 col-xs-12">
                    <ul id="comments" class="list-group">
                        <li class="list-group-item"><span class="glyphicon glyphicon-comment"></span> 댓글</li>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li id="li-comment-form" class="list-group-item">
                                <form id="comment-form" role="form">
                                    <div class="form-group">
                                        <textarea id="comment-textarea" class="form-control" rows="3"></textarea>
                                    </div>
                                    <div class="clearfix">
                                        <button type="submit" class="btn btn-primary pull-right">댓글쓰기</button>
                                    </div>
                                </form>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
                            <li id="disabled-comment" class="list-group-item">
                                <form role="form">
                                    <div class="form-group">
                                        <textarea class="form-control" placeholder="로그인해 주세요!" rows="3"
                                                  disabled></textarea>
                                    </div>
                                    <div class="clearfix">
                                        <button type="button" class="btn btn-primary pull-right" disabled>댓글쓰기</button>
                                    </div>
                                </form>
                            </li>
                        </sec:authorize>
                        <c:forEach var="comment" items="${article.comments}">
                            <li id="li-comment-${comment.id}" class="list-group-item">
                                <h4>${comment.user.displayName}
                                    <small><c:if test='${comment.user.role > 5}'><span
                                            class="glyphicon glyphicon-home"></span> </c:if> <fmt:formatDate
                                            pattern="yyyy-MM-dd HH:mm" value="${comment.published}"/><sec:authorize
                                            access="hasRole('ROLE_ADMIN') or authentication.name == #comment.user.email">
                                        <a id="del-comment-${comment.id}" class="del-comment"
                                           href="/api/article/${article.id}/${comment.id}"> 삭제</a></sec:authorize>
                                    </small>
                                </h4>
                                <p><c:out value="${comment.content}" escapeXml="true"/></p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="blueimp-gallery" class="blueimp-gallery">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>
<sec:authorize access="hasRole('ROLE_ADMIN') or authentication.name == #article.user.email">
    <!-- Modal -->
    <div class="modal fade" id="article-del-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">체험 후기 삭제</h4>
                </div>
                <form id="login-form" role="form">
                    <div class="modal-body">
                        <div class="bs-callout bs-callout-danger">
                            <h4>주의!</h4>

                            <p>체험 후기글에 연결된 모든 사진과 댓글도 함께 삭제됩니다. 삭제하시겠습니까?</p>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-lg" data-dismiss="modal">취소</button>
                        <button type="button" id="article-del" data-uri="/api/article/${article.id}"
                                class="btn btn-danger btn-lg">삭제
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $("#article-del").click(function (e) {
                e.preventDefault();
                var uri = $(this).attr("data-uri");
                $.ajax({
                    url: uri,
                    type: "POST",
                    data: {
                        _method: "DELETE"
                    }
                }).done(function () {
                    document.location.href = "/article";
                });
            });
        });
    </script>
</sec:authorize>
<script src="/resources/js/comment.js"></script>
<script src="/resources/js/blueimp-gallery.min.js"></script>
<script>
    $(document).ready(function () {
        document.getElementById('article-photos').onclick = function (event) {
            event = event || window.event;
            var target = event.target || event.srcElement,
                    link = target.src ? target.parentNode : target,
                    options = {index: link, event: event},
                    links = this.getElementsByTagName('a');
            blueimp.Gallery(links, options);
        }
    });
</script>