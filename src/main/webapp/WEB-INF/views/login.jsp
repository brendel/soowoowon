<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAnonymous()">
    <!-- Modal -->
    <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">로그인</h4>
                </div>
                <form id="login-form" role="form">
                    <div class="modal-body">
                        <div id="reservation-callout" class="login-modal-callout bs-callout bs-callout-info hidden">
                            <h4>로그인이 필요한 페이지입니다.</h4>

                            <p>모든 예약정보는 회원 본인과 관리자만 볼 수 있습니다.</p>
                        </div>
                        <div class="form-group">
                            <label for="email-input">이메일 주소</label>
                            <input type="email" name="j_username" class="form-control input-lg"
                                   id="email-input"
                                   placeholder="example@email.com"
                                   min="6" required autofocus>
                        </div>
                        <div class="form-group">
                            <label for="password-input">비밀번호</label>
                            <input type="password" name="j_password" class="form-control input-lg" id="password-input"
                                   min="6"
                                   placeholder="password" required>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" name="remember_me"> 기억하기</label>
                        </div>
                        <div id="login-alert" class="alert alert-danger hidden">
                            로그인 정보가 올바르지 않습니다. 다시 확인해 주세요!
                        </div>
                        <div class="text-right">
                            <a href="/signup">
                                <small>아직 계정이 없으시면 회원가입 해주세요!</small>
                            </a>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="login-btn" type="submit" class="btn btn-primary btn-lg">로그인</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="/resources/js/login.js"></script>
</sec:authorize>
