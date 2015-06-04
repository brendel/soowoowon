<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main" class="container">
    <div class="page-header">
        <h2>가입하기</h2>
    </div>
    <div class="row">
        <div class="col-md-9 col-sm12 col-xs-12">
            <div class="row">
                <div class="col-md-12 col-sm12 col-xs-12">
                    <div id="signup-help">
                        <div class="bs-callout bs-callout-info">
                            <h4>가입 안내</h4>

                            <p>간편하게 몇가지 정보만 입력하시면 가입 됩니다.<br>
                                수우원은 실명 가입을 요구하지 않으며, 이메일이나 휴대폰 번호로 <strong>광고성 콘텐츠</strong>를 보내지 않습니다. 이메일이나 휴대폰 번호는
                                웹페이지나
                                검색엔진에 노출 되지
                                않으며, 문의하신 내용에 대한 답변이나 중요한 변경사항이 있을 경우에만 회원 정보를 사용해 연락드립니다.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <form id="signup-form" role="form">
                <div class="row">
                    <div class="form-group col-md-12 col-sm12 col-xs-12 ">
                        <label for="signup-email">이메일 주소(아이디로 사용됩니다)</label>
                        <input type="email" name="email" class="form-control input-lg"
                               id="signup-email"
                               placeholder="이메일 주소를 입력하세요"
                               autofocus="true" required>

                        <div id="email-error" class="text-danger hidden">올바른 이메일 주소가 아닙니다. 다시 확인해주세요!</div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12 col-sm12 col-xs-12 ">
                        <label for="signup-password">비밀번호</label>
                        <input type="password" name="password" class="form-control input-lg" id="signup-password"
                               placeholder="비밀번호(6자 이상)" required>

                        <div id="password-error" class="text-danger hidden">비밀번호는 6자 이상으로 입력해주세요!</div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12 col-sm12 col-xs-12">
                        <label for="signup-password-confirm">비밀번호 확인</label>
                        <input type="password" name="passwordConfirm" class="form-control input-lg"
                               id="signup-password-confirm"
                               placeholder="비밀번호 확인(6자 이상)" required>

                        <div id="password-confirm-error" class="text-danger hidden pw-confirm-error">비밀번호가 위와 동일하지
                            않습니다.
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12 col-sm12 col-xs-12 ">
                        <label for="signup-name">성함 또는 별명</label>
                        <input type="text" name="displayName" class="form-control input-lg" id="signup-name"
                               placeholder="이름을 입력하세요(2자 이상)" required>

                        <div id="displayName-error" class="text-danger hidden">올바른 이름이 아닙니다(2자 이상 8자 이하, 특수문자 제외)</div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12 col-sm12 col-xs-12 ">
                        <label for="signup-phone">연락 가능한 휴대폰 번호</label>
                        <input type="tel" name="phone" class="form-control input-lg" id="signup-phone"
                               placeholder="휴대폰 번호를 입력하세요" required>

                        <div id="phone-error" class="text-danger hidden">올바른 휴대폰 번호가 아닙니다. 다시 확인해주세요!</div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12 col-sm12 col-xs-12 ">
                        <button id="singin-submit" type="submit" class="btn btn-primary btn-lg">가입하기</button>
                        <button class="btn btn-default btn-lg">돌아가기</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/resources/js/signup.js"></script>
