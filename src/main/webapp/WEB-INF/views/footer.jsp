<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="footer" class="text-center">
    <hr>
    <small>Copyright ⓒ 2006-2014, Soowoowon(수우원).<br>All Rights Reserved.</small>
</div>
<script>
    var isLoggedIn = <sec:authorize access="isAnonymous()">false;
    </sec:authorize><sec:authorize access="isAuthenticated()">true;
    </sec:authorize>
</script>