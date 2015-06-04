<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="main" class="container">
    <div class="page-header">
        <h2>체험문의/예약하기</h2>
    </div>
    <div class="row">
        <div class="col-md-9 col-sm12 col-xs-12">
            <form id="reservation-form" role="form">
                <div class="row">
                    <div class="col-md-12 col-sm12 col-xs-12 form-group">
                        <label for="reservation-title">글 제목</label>
                        <input type="text" name="title" class="form-control input-lg" id="reservation-title"
                               placeholder="글 제목을 입력하세요"
                               value="<c:out value='${reservation.title}' />" required>
                    </div>
                </div>
                <div class="row">
                    <div id="date-area" class="col-md-4 col-sm-4 col-xs-12 form-group">
                        <label for="reservation-date">희망 예약일</label>

                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            <input type="text" name="date" class="form-control" id="reservation-date"
                                   placeholder="2014-08-05"
                                   value="<fmt:formatDate pattern="yyyy-MM-dd" value='${reservation.date}'/>" readonly
                                   required>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-4 col-xs-12 form-group">
                        <label for="reservation-part">시간</label>

                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                            <select id="reservation-part" class="form-control" name="part">
                                <option value="1" <c:if test='${reservation.part == 1}'>selected</c:if>>1부
                                    (11:00-15:00)
                                </option>
                                <option value="2" <c:if test='${reservation.part == 2}'>selected</c:if>>2부
                                    (14:00-17:00)
                                </option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-2 col-xs-6 form-group">
                        <label for="reservation-adult">어른</label>

                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            <select id="reservation-adult" class="form-control" name="adult">
                                <c:forEach var="n" begin="0" end="50">
                                    <option value="${n}"
                                            <c:if test='${reservation.adult == n}'>selected</c:if>>${n}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-2 col-xs-6 form-group">
                        <label for="reservation-child">아이</label>

                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            <select id="reservation-child" class="form-control" name="child">
                                <c:forEach var="n" begin="0" end="50">
                                    <option value="${n}"
                                            <c:if test='${reservation.child == n}'>selected</c:if>>${n}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-sm12 col-xs-12 form-group">
                        <label for="reservation-content">글 내용</label>
                        <textarea name="content" class="form-control input-lg" id="reservation-content"
                                  placeholder="글 내용을 입력하세요" rows="10" required><c:out
                                value='${reservation.content}'/></textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-sm12 col-xs-12 form-group">
                        <button id="reservation-submit" type="submit" class="btn btn-primary btn-lg">업데이트</button>
                        <a href="/reservation">
                            <button type="button" class="btn btn-default btn-lg">취소</button>
                        </a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/resources/js/reservation.js"></script>