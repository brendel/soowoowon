<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="main" class="container">
    <div class="page-header">
        <h2>체험문의/예약하기</h2>
    </div>
    <div class="row">
        <div class="col-md-9 col-sm12 col-xs-12">
            <div class="row">
                <div class="col-md-12 col-sm12 col-xs-12">
                    <div id="article-detail-panel" class="panel panel-default">
                        <div class="panel-heading">
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <div class="btn-group pull-right">
                                    <button id="reservationState-btn" type="button"
                                            class="btn btn-sm ${stateCss[reservation.reservationState]} dropdown-toggle"
                                            data-toggle="dropdown">
                                            ${stateText[reservation.reservationState]} <span class="caret"></span>
                                    </button>
                                    <ul id="reservationState-menu" class="dropdown-menu" role="menu">
                                        <c:forEach var="s" items="${stateText}">
                                            <li><a class="reservationState-menu-item" href="#">${s}</a></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </sec:authorize>
                            <sec:authorize
                                    access="authentication.name == #reservation.user.email and !hasRole('ROLE_ADMIN')">
                                <button class="btn ${stateCss[reservation.reservationState]} btn-sm pull-right">${stateText[reservation.reservationState]}</button>
                            </sec:authorize>
                            <h4>${reservation.title}</h4>

                            <p>${reservation.user.displayName} | <fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                                                                                 value="${reservation.published}"/><sec:authorize
                                    access="hasRole('ROLE_ADMIN') or authentication.name == #reservation.user.email">
                                <small><a href="/reservation/update/${reservation.id}">수정</a> | <a href="#"
                                                                                                   data-toggle="modal"
                                                                                                   data-target="#reservation-del-modal">삭제</a>
                                </small>
                            </sec:authorize></p>
                        </div>
                        <div class="panel-body">
                            <div id="detail-content" class="row">
                                <div class="col-md-12">
                                    <c:out value="${reservation.content}" escapeXml="false"/>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <div class="row">
                                <div class="col-md-4 col-sm-4 col-xs-12">
                                    <span class="glyphicon glyphicon-calendar"></span> 예약일 ${reservation.date}
                                </div>
                                <div class="col-md-4 col-sm-4 col-xs-12">
                                    <span class="glyphicon glyphicon-time"></span> ${part}
                                </div>
                                <div class="col-md-2 col-sm-2 col-xs-12">
                                    <span class="glyphicon glyphicon-user"></span> 아이 ${reservation.child}인
                                </div>
                                <div class="col-md-2 col-sm-2 col-xs-12">
                                    <span class="glyphicon glyphicon-user"></span> 어른 ${reservation.adult}인
                                </div>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <div class="col-md-4 col-sm-4 col-xs-12">
                                        <span class="glyphicon glyphicon-envelope"></span> <a
                                            href="mailto:${reservation.user.email}">${reservation.user.email}</a>
                                    </div>
                                    <div class="col-md-4 col-sm-4 col-xs-12">
                                        <span class="glyphicon glyphicon-earphone"></span> <a
                                            href="tel:${reservation.user.phone}">${reservation.user.phone}</a>
                                    </div>
                                </sec:authorize>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 col-sm12 col-xs-12">
                    <ul id="comments" class="list-group">
                        <li class="list-group-item"><span class="glyphicon glyphicon-comment"></span> 댓글</li>
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
                        <c:forEach var="comment" items="${reservation.comments}">
                            <li id="li-comment-${comment.id}" class="list-group-item">
                                <h4>${comment.user.displayName}
                                    <small><c:if test='${comment.user.role > 5}'><span
                                            class="glyphicon glyphicon-home"></span> </c:if> <fmt:formatDate
                                            pattern="yyyy-MM-dd HH:mm" value="${comment.published}"/><sec:authorize
                                            access="hasRole('ROLE_ADMIN') or authentication.name == #comment.user.email">
                                        <a id="del-comment-${comment.id}" class="del-comment"
                                           href="/api/reservation/${reservation.id}/${comment.id}">
                                            삭제</a></sec:authorize>
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
<sec:authorize access="hasRole('ROLE_ADMIN') or authentication.name == #reservation.user.email">
    <!-- Modal -->
    <div class="modal fade" id="reservation-del-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">에약 내역 삭제</h4>
                </div>
                <form id="login-form" role="form">
                    <div class="modal-body">
                        <div class="bs-callout bs-callout-danger">
                            <h4>주의!</h4>

                            <p>모든 예약 정보와 댓글이 삭제됩니다. 삭제하시겠습니까?</p>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-lg" data-dismiss="modal">취소</button>
                        <button type="button" id="reservation-del" data-uri="/api/reservation/${reservation.id}"
                                class="btn btn-danger btn-lg">삭제
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $("#reservation-del").click(function (e) {
                e.preventDefault();
                var uri = $(this).attr("data-uri");
                $.ajax({
                    url: uri,
                    type: "POST",
                    data: {
                        _method: "DELETE"
                    }
                }).done(function () {
                    document.location.href = "/reservation";
                });
            });
        });
    </script>
</sec:authorize>
<script src="/resources/js/comment.js"></script>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <script src="/resources/js/reservation.extra.js"></script>
</sec:authorize>