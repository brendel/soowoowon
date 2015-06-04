<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="main" class="container">
    <div id="userinfo-header" class="page-header">
        <h2>회원정보</h2>
    </div>
    <div class="row">
        <div class="col-md-12 col-sm12 col-xs-12">
            <!-- Nav tabs -->
            <ul id="userinfo-tabs" class="nav nav-tabs" role="tablist">
                <li class="active"><a href="#profile" role="tab" data-toggle="tab">프로필</a></li>
                <li><a href="#edit-user" role="tab" data-toggle="tab">회원정보수정</a></li>
                <li><a href="#edit-password" role="tab" data-toggle="tab">비밀번호변경</a></li>
            </ul>
        </div>
    </div>
    <div class="tab-content row">
        <div id="profile" class="tab-pane fade in active col-md-9 col-sm12 col-xs-12">
            <div class="row">
                <div class="col-md-12 col-sm12 col-xs-12">
                    <div id="alert-area"></div>
                    <div>
                        <h4>이메일주소(아이디)</h4>

                        <p><c:out value="${user.email}"/></p>
                    </div>
                    <div>
                        <h4>성함 또는 닉네임</h4>

                        <p id="profile-name"><c:out value="${user.displayName}"/></p>
                    </div>
                    <div>
                        <h4>연락 가능한 휴대폰 번호</h4>

                        <p id="profile-phone"><c:out value="${user.phone}"/></p>
                    </div>
                </div>
            </div>
        </div>
        <div id="edit-user" class="tab-pane fade col-md-9 col-sm12 col-xs-12">
            <form id="edit-user-form" role="form">
                <div class="row">
                    <div class="form-group col-md-12 col-sm12 col-xs-12 ">
                        <label for="edit-user-name">성함 또는 별명</label>
                        <input type="text" name="displayName" class="form-control input-lg" id="edit-user-name"
                               placeholder="새로운 이름을 입력하세요(2자 이상)" value="<c:out value='${user.displayName}'/>"
                               required>

                        <div id="displayName-error" class="text-danger hidden">올바른 이름이 아닙니다(2자 이상 8자 이하, 특수문자 제외)</div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12 col-sm12 col-xs-12 ">
                        <label for="edit-user-phone">연락 가능한 휴대폰 번호</label>
                        <input type="tel" name="phone" class="form-control input-lg" id="edit-user-phone"
                               placeholder="새로운 휴대폰 번호를 입력하세요" value="<c:out value='${user.phone}'/>" required>

                        <div id="phone-error" class="text-danger hidden">올바른 휴대폰 번호가 아닙니다. 다시 확인해주세요!</div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12 col-sm12 col-xs-12 ">
                        <button id="edit-user-submit" type="submit" class="btn btn-primary btn-lg">수정하기</button>
                        <a href="/userinfo">
                            <a href="/userinfo">
                                <button type="button" class="btn btn-default btn-lg">취소</button>
                            </a>
                        </a>
                    </div>
                </div>
            </form>
        </div>
        <div id="edit-password" class="tab-pane fade col-md-9 col-sm12 col-xs-12">
            <form id="edit-password-form" role="form">
                <div class="row">
                    <div class="form-group col-md-12 col-sm12 col-xs-12 ">
                        <label for="old-password">현재 비밀번호</label>
                        <input type="password" name="oldPassword" class="form-control input-lg" id="old-password"
                               placeholder="현재 비밀번호(6자 이상)" required>

                        <div id="oldPassword-error" class="text-danger hidden">비밀번호는 6자 이상으로 입력해주세요!</div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12 col-sm12 col-xs-12 ">
                        <label for="new-password">새 비밀번호</label>
                        <input type="password" name="newPassword" class="form-control input-lg" id="new-password"
                               placeholder="비밀번호(6자 이상)" required>

                        <div id="newPassword-error" class="text-danger hidden">비밀번호는 6자 이상으로 입력해주세요!</div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12 col-sm12 col-xs-12">
                        <label for="new-password-confirm">새 비밀번호 확인</label>
                        <input type="password" name="newPasswordConfirm" class="form-control input-lg"
                               id="new-password-confirm"
                               placeholder="새 비밀번호 확인(6자 이상)" required>

                        <div id="newPassword-confirm-error" class="text-danger hidden pw-confirm-error">비밀번호가 위와 동일하지
                            않습니다.
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12 col-sm12 col-xs-12 ">
                        <button id="edit-password-submit" type="submit" class="btn btn-primary btn-lg">변경하기</button>
                        <a href="/userinfo">
                            <button type="button" class="btn btn-default btn-lg">취소</button>
                        </a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/resources/js/userinfo.js"></script>