<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main" class="container">
    <div class="page-header">
        <h2>문의/예약내역</h2>
    </div>
    <div class="row">
        <div class="col-md-12 col-sm12 col-xs-12">
            <form id="reservation-search-form" role="form">
                <div class="row">
                    <div class="col-md-2 col-sm-3 col-xs-12 form-group">
                        <div class="input-group">
                            <input type="text" class="form-control" id="reservation-from"
                                   placeholder="From" readonly>
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-3 col-xs-12 form-group">
                        <div class="input-group">
                            <input type="text" class="form-control" id="reservation-to"
                                   placeholder="To" readonly>
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-3 col-xs-12 form-group">
                        <div class="input-group">
                            <select id="reservation-reservationState" class="form-control"
                                    name="reservation-reservationState">
                                <option value="-1" selected>전체</option>
                                <option value="0">취소 됨</option>
                                <option value="1">검토 중</option>
                                <option value="2">답변 완료</option>
                                <option value="3">예약 확정</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-3 col-xs-12 form-group">
                        <button id="reservation-search-submit" type="submit" class="btn btn-primary">조회</button>
                        <button id="reservation-search-xls" class="btn btn-default">엑셀</button>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">예약내역</div>
                        <div class="table-responsive">
                            <table class="table table-condensed">
                                <thead>
                                <th>#</th>
                                <th>예약일</th>
                                <th>제목</th>
                                <th>어른</th>
                                <th>아이</th>
                                <th>파트</th>
                                <th>이름</th>
                                <th>이메일</th>
                                <th>휴대폰 번호</th>
                                <th>작성일</th>
                                <th>상태</th>
                                </thead>
                                <tbody id="reservation-search-result">

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<form class="hidden" id="xls-form" action="/admin/reservation/search.xls" method="post">
    <input type="text" id="from" name="from"/>
    <input type="text" id="to" name="to"/>
    <input type="text" id="reservationState" name="reservationState"/>
</form>

<script src="/resources/js/admin/reservation-search.js"></script>