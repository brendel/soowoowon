<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<li style="opacity: 0;" class="list-group-item list-group-item-success">
    <h4>${comment.user.displayName}
        <small><c:if test='${comment.user.role > 5}'><span class="glyphicon glyphicon-home"></span> </c:if>
            <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${comment.published}"/> <a class="del-comment"
                                                                                         href="/api/article/1/${comment.id}">
                삭제</a>
        </small>
    </h4>
    <p><c:out value="${comment.content}"/></p>
</li>