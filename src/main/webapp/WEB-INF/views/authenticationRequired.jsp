<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="main" class="container">
    <div class="page-header">
        <h2>로그인이 필요한 페이지</h2>
    </div>
    <div class="row">
        <div class="col-md-9 col-sm-12 col-xs-12">
            <div class="row">
                <div class="col-md-12 col-sm12 col-xs-12">
                    <div id="signup-help">
                        <div class="bs-callout bs-callout-info">
                            <h4>안내</h4>

                            <p>로그인이 필요한 페이지 입니다.<br>
                                예약/문의 내용은 개인정보 노출을 막기 위해 작성자만 조회할 수 있습니다.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row text-center">
                <div class="col-md-12 col-sm12 col-xs-12">
                    <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#login-modal">로그인</button>
                    <a href="/signup">
                        <button class="btn btn-lg">회원가입</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>