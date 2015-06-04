<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="main" class="container">
    <div class="page-header">
        <h2>SMS 발송내역</h2>
    </div>
    <div class="row">
        <div class="col-md-9 col-sm-12 col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading">SMS 발송내역</div>
                <div class="table-responsive">
                    <table class="table table-condensed">
                        <thead>
                        <th>#</th>
                        <th>코드</th>
                        <th>발송시간</th>
                        <th>수신인</th>
                        <th>핸드폰</th>
                        <th>SMS 메세지</th>
                        </thead>
                        <tbody>
                        <c:forEach var="m" items="${smsList}">
                            <tr>
                                <td>${m.id}</td>
                                <td>${m.resultCode}</td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${m.sent}"/></td>
                                <td><c:out value="${m.receiver.displayName}"/></td>
                                <td><a href="tel:${m.receiver.phone}">${m.receiver.phone}</a></td>
                                <td><c:out value="${m.message}"/></td>
                                <td></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
