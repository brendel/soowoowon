<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="reservation-hero" class="edufarm-hero">
    <div class="container">
        <div class="jumbotron">
            <h1>궁금하신 점이 있으신가요?</h1>

            <h3>교육 농장에 대해 궁금하신 점이나 예약 문의가 있으시면 성실히 답변해 드리겠습니다. 모든 예약정보는 회원 본인과 관리자만 볼 수 있습니다.</h3>

            <p><a href="/reservation/new" class="btn btn-primary btn-lg" role="button">예약 문의하기</a></p>
        </div>
    </div>
</div>
<div id="main" class="container">
    <c:if test="${post != null}">
        <div class="row">
            <div class="col-md-9 col-sm12 col-xs-12">
                <div class="bs-callout bs-callout-info">
                    <h4><c:out value="${post.title}"/></h4>

                    <p><c:out value="${post.content}"/></p>
                </div>
            </div>
        </div>
    </c:if>
    <div class="row">
        <div class="col-md-9 col-sm12 col-xs-12">
            <div id="reservation-list" class="list-group">
                <c:forEach var="reservation" items="${reservationList}">
                    <a href="/reservation/detail/${reservation.id}"
                       class="list-group-item <sec:authorize access='(isAuthenticated() and !hasRole(\'ROLE_ADMIN\')) and principal.username != #reservation.user.email'> access-denied</sec:authorize>">
                        <h4 class="list-group-item-heading">${reservation.title} <span
                                class="label ${stateCss[reservation.reservationState]} pull-right">${stateText[reservation.reservationState]}</span>
                        </h4>

                        <p class="list-group-item-text">
                            <small><span class="glyphicon glyphicon-lock"></span> ${reservation.user.displayName} |
                                <fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                                                value="${reservation.published}"/></small>
                        </p>
                    </a>
                </c:forEach>
            </div>
            <div class="row text-center">
                <ul class="pagination">
                    <li class="<c:if test='${currPage == 1}'>disabled</c:if>"><a
                            href="<c:if test='${currPage != 1}'>/reservation/${currPage - 1}</c:if>">&laquo;</a></li>
                    <c:forEach var="i" begin="1" end="${lastPage}">
                        <li class="<c:if test='${currPage == i}' >active</c:if>"><a href="/reservation/${i}">${i}</a>
                        </li>
                    </c:forEach>
                    <li class="<c:if test='${currPage == lastPage}'>disabled</c:if>"><a
                            href="<c:if test='${currPage != lastPage}'>/reservation/${currPage + 1}</c:if>">&raquo;</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<sec:authorize access="isAnonymous()">
    <script src="/resources/js/loginRequired.js"></script>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <div class="modal fade" id="r-access-modal" tabindex="-1" role="dialog"
         aria-labelledby="r-access-modal-label"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h3 class="modal-title" id="r-access-modal-label">권한이 없습니다.</h3>
                </div>
                <div class="modal-body">
                    <p>예약/문의 내역은 개인정보 보호를 위해 작성자와 관리자만 조회할 수 있습니다.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-primary" data-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>
    <script src="/resources/js/accessDenied.js"></script>
</sec:authorize>
