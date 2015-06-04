<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header id="edufarm-navbar" class="navbar bs-docs-nav" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">수우원 에듀팜</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a id="link-to-reservation" href="/reservation">체험문의/예약</a></li>
                <li><a href="/article">후기</a></li>
                <li><a href="/gallery">사진첩</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAnonymous()">
                    <li><a href="/signup">가입하기</a></li>
                    <li><a id="login" href="#" data-toggle="modal" data-target="#login-modal">로그인</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li><a href="/userinfo">회원정보</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="/admin">관리자</a></li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li><a id="logout" href="/logout">로그아웃</a></li>
                </sec:authorize>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</header>