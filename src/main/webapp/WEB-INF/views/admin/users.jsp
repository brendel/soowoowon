<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="main" class="container">
    <div class="page-header">
        <h2>회원정보</h2>
    </div>
    <div class="row">
        <div class="col-md-9 col-sm-12 col-xs-12">
            <div class="panel panel-default">
                <div class="panel-heading">회원정보</div>
                <div class="table-responsive">
                    <table class="table table-condensed">
                        <thead>
                        <th>#</th>
                        <th>이름</th>
                        <th>이메일</th>
                        <th>핸드폰</th>
                        <th>마지막 방문</th>
                        <th>가입일</th>
                        <th>등급</th>
                        </thead>
                        <tbody>
                        <c:forEach var="u" items="${userList}">
                            <tr>
                                <td>${u.id}</td>
                                <td><c:out value="${u.displayName}"/></td>
                                <td><a href="mailto:${u.email}">${u.email}</a></td>
                                <td><a href="tel:${u.phone}">${u.phone}</a></td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${u.lastVisited}"/></td>
                                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${u.joined}"/></td>
                                <td>${u.role}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
