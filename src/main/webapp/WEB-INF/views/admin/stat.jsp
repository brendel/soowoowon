<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="main" class="container">
    <div class="page-header">
        <h2>사이트 통계</h2>
    </div>
    <div class="row">
        <div class="col-md-12 col-sm12 col-xs-12">
            <div class="row">
                <div class="col-md-4 col-sm12 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">일별 방문자</div>
                        <div class="table-responsive">
                            <table class="table table-condensed">
                                <thead>
                                <th>#</th>
                                <th>날짜</th>
                                <th class="text-right">방문자</th>
                                </thead>
                                <tbody>
                                <c:set var="i" value="0"/>
                                <c:forEach var="stat" items="${dateStat}">
                                    <tr>
                                        <c:set var="i" value="${i + 1}"/>
                                        <td>${i}</td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${stat[0]}"/></td>
                                        <td class="text-right">${stat[1]}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm12 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">방문자 OS</div>
                        <div class="table-responsive">
                            <table class="table table-condensed">
                                <thead>
                                <th>#</th>
                                <th>OS</th>
                                <th class="text-right">백분율</th>
                                </thead>
                                <tbody>
                                <c:set var="i" value="0"/>
                                <c:forEach var="stat" items="${osStat}">
                                    <tr>
                                        <c:set var="i" value="${i + 1}"/>
                                        <td>${i}</td>
                                        <td>${stat[0]}</td>
                                        <td class="text-right"><fmt:formatNumber value="${stat[1] / monthTotal}"
                                                                                 type="PERCENT"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-sm12 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">방문자 웹브라우저</div>
                        <div class="table-responsive">
                            <table class="table table-condensed">
                                <thead>
                                <th>#</th>
                                <th>Web Browser</th>
                                <th class="text-right">백분율</th>
                                </thead>
                                <tbody>
                                <c:set var="i" value="0"/>
                                <c:forEach var="stat" items="${browserStat}">
                                    <tr>
                                        <c:set var="i" value="${i + 1}"/>
                                        <td>${i}</td>
                                        <td>${stat[0]}</td>
                                        <td class="text-right"><fmt:formatNumber value="${stat[1] / monthTotal}"
                                                                                 type="PERCENT"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 col-sm12 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">방문자 리퍼러</div>
                        <div class="table-responsive">
                            <table class="table table-condensed">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>리퍼러</th>
                                    <th class="text-right">백분율</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:set var="i" value="0"/>
                                <c:forEach var="stat" items="${referrerStat}">
                                    <tr>
                                        <c:set var="i" value="${i + 1}"/>
                                        <td>${i}</td>
                                        <td>${stat[0]}</td>
                                        <td class="text-right"><fmt:formatNumber value="${stat[1] / monthTotal}"
                                                                                 type="PERCENT"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 col-sm12 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">방문 기록</div>
                        <div class="table-responsive">
                            <table class="table table-condensed">
                                <thead>
                                <th>#</th>
                                <th>시간</th>
                                <th>리퍼러</th>
                                <th>IP</th>
                                <th>OS</th>
                                <th>Web Browser</th>
                                </thead>
                                <tbody>
                                <c:forEach var="stat" items="${visits}">
                                    <tr>
                                        <td>${stat.id}</td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${stat.visited}"/></td>
                                        <td>${stat.referrer}</td>
                                        <td>${stat.IP}</td>
                                        <td>${stat.OS}</td>
                                        <td>${stat.webBrowser}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>