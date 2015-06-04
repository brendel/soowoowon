<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header id="edufarm-navbar" class="navbar bs-docs-nav" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/admin">수우원 에듀팜 관리</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/admin/users">회원정보</a></li>
                <li><a href="/admin/reservation">예약내역</a></li>
                <li><a href="/admin/sms">SMS</a></li>
                <li><a href="/admin/editHome">첫페이지</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/">홈으로</a></li>
                <li><a href="/logout">로그아웃</a></li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</header>