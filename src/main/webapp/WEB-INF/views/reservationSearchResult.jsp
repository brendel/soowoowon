<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach var="r" items="${reservationList}">
    <tr>
        <td>${r.id}</td>
        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${r.date}"/></td>
        <td><a href="/reservation/detail/${r.id}" target="_blank"><c:out value="${r.title}"/></a></td>
        <td>${r.adult}</td>
        <td>${r.child}</td>
        <td>${r.part}</td>
        <td><c:out value="${r.user.displayName}"/></td>
        <td><a href="mailto:${r.user.email}">${r.user.email}</a></td>
        <td><a href="tel:${r.user.phone}">${r.user.phone}</a></td>
        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${r.published}"/></td>
        <td>${stateText[r.reservationState]}</td>
    </tr>
</c:forEach>