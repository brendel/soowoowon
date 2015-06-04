<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@env.getProperty('cdn.host')" var="cdn"/>
<div id="home-hero" class="edufarm-hero">
    <div class="container">
        <div class="jumbotron">
            <h1>환영합니다!</h1>

            <h3>수우원 에듀팜은 유기농 블루베리 수우원이 운영하는 에듀팜 인증 체험교육농장입니다.</h3>

            <p><a href="#about" class="btn btn-primary btn-lg" role="button">더 알아보기</a></p>
        </div>
    </div>
</div>
<div id="main" class="container">
    <div class="row">
        <img class="img-responsive" src="${cdn}/files/home/big_title.jpg"/>

        <div class="col-md-12 col-sm-12 col-xs-12">
            <h2 id="about" class="page-header">수우원 소개 <a href="http://www.soowoowon.com" target="_blank">
                <button type="button" class="btn btn-primary btn-sm">수우원 홈페이지</button>
            </a></h2>
        </div>
        <div class="col-md-8 col-sm-12 col-xs-12">
            <h4>국립농산물품질관리원 인증 유기농 농장</h4>

            <p>저희 수우원은 한국에 블루베리를 처음 소개하고 유기농 인증을 받은 농장 중에 한 곳으로, 파주 민통선 인근의 청정지역에서 단 0.01%의 농약과 화학비료도 사용하지 않은 유기농 블루베리를
                재배하고 있습니다.</p>
        </div>
        <div class="col-md-4 col-sm-12 col-xs-12">
            <img class="img-responsive" src="${cdn}/files/home/organic.png"/>
        </div>
        <div class="col-md-12 col-sm-12 col-xs-12">
            <h2 class="page-header">수우원 에듀팜은?</h2>
        </div>
        <div class="col-md-9 col-sm-12 col-xs-12">
            <h4>경기도 농업기술원이 지정한 에듀팜 체험교육농장</h4>
            <h4>농촌 진흥청이 평가하여 품질인증이 된 체험교육농장</h4>

            <p>"나무의 친구"라는 뜻을 가진 수우원 교육농장은 도시생활에 익숙한 우리 친구들이 자연이 주는 소중함을 직접 체험할 수 있는 공간입니다. 또한 유기농
                블루베리의 자라는 과정과 유기농 식물의 자라는 환경 등을 직접 조사하고 관찰할 수 있는 곳입니다. 넓은 농장에서 마음껏 뛰어다니며 즐겁게 배울 수 있는 수우원 교육농장에서 아이들의 꿈과
                정서가 아름답게 자라길 바랍니다.</p>
        </div>
        <div class="col-md-3 col-sm-12 col-xs-12">
            <img class="img-responsive" src="${cdn}/files/home/edufarm_logo.gif"/>
        </div>
    </div>
    <div id="home-photos" class="row">
        <div class="col-md-3 col-sm-3 col-xs-6">
            <a href="${cdn}/files/home/home1.jpg" title="시원한 물놀이">
                <img class="img-responsive" src="${cdn}/files/home/thumb/home1t.jpg"/>
            </a>
        </div>
        <div class="col-md-3 col-sm-3 col-xs-6">
            <a href="${cdn}/files/home/home2.jpg" title="블루베리 수확체험">
                <img class="img-responsive" src="${cdn}/files/home/thumb/home2t.jpg"/>
            </a>
        </div>
        <div class="col-md-3 col-sm-3 col-xs-6">
            <a href="${cdn}/files/home/home3.jpg" title="잔디밭에서 놀아요!">
                <img class="img-responsive" src="${cdn}/files/home/thumb/home3t.jpg"/>
            </a>
        </div>
        <div class="col-md-3 col-sm-3 col-xs-6">
            <a href="${cdn}/files/home/home4.jpg" title="두 손 가득 블루베리">
                <img class="img-responsive" src="${cdn}/files/home/thumb/home4t.jpg"/>
            </a>
        </div>
        <div class="col-md-3 col-sm-3 col-xs-6">
            <a href="${cdn}/files/home/home5.jpg" title="시원한 물놀이">
                <img class="img-responsive" src="${cdn}/files/home/thumb/home5t.jpg"/>
            </a>
        </div>
        <div class="col-md-3 col-sm-3 col-xs-6">
            <a href="${cdn}/files/home/home6.jpg" title="유기농 블루베리 따기">
                <img class="img-responsive" src="${cdn}/files/home/thumb/home6t.jpg"/>
            </a>
        </div>
        <div class="col-md-3 col-sm-3 col-xs-6">
            <a href="${cdn}/files/home/home7.jpg" title="자연을 느껴 보아요">
                <img class="img-responsive" src="${cdn}/files/home/thumb/home7t.jpg"/>
            </a>
        </div>
        <div class="col-md-3 col-sm-3 col-xs-6">
            <a href="${cdn}/files/home/home8.jpg" title="안토시아닌이 가득한 블루베리">
                <img class="img-responsive" src="${cdn}/files/home/thumb/home8t.jpg"/>
            </a>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <h2 class="page-header">프로그램 <a href="/reservation">
                <button type="button" class="btn btn-primary btn-sm">문의/예약하기</button>
            </a></h2>
        </div>
        <div class="col-md-4 col-sm-12 col-xs-12 home-program">
            <h4 class="page-header">어린이집, 유치원 활동</h4>

            <div class="row">
                <div class="col-md-2 col-sm-2 col-xs-3"><strong>봄</strong></div>
                <div class="col-md-10 col-sm-10 col-xs-9">블루베리 묘목 심어 가져가기, 쿠키 만들기, 동물 먹이주기, 솜사탕</div>
            </div>
            <div class="row">
                <div class="col-md-2 col-sm-2 col-xs-3"><strong>여름</strong></div>
                <div class="col-md-10 col-sm-10 col-xs-9">블루베리 따기 체험, 블루베리 빙수 만들기, 물놀이</div>
            </div>
            <div class="row">
                <div class="col-md-2 col-sm-2 col-xs-3"><strong>가을</strong></div>
                <div class="col-md-10 col-sm-10 col-xs-9">고구마, 땅콩, 사과 중 선택 체험, 떡 만들기, 동물 먹이 주기</div>
            </div>
            <div class="row">
                <div class="col-md-2 col-sm-2 col-xs-3"><strong>겨울</strong></div>
                <div class="col-md-10 col-sm-10 col-xs-9">김치 만들기 체험, 군밤 구워먹기, 블루베리잼 만들기, 블루베리 초콜릿 만들기</div>
            </div>
        </div>
        <div class="col-md-4 col-sm-12 col-xs-12 home-program">
            <h4 class="page-header">초등학교 활동</h4>

            <p><strong>교과서 연계 교육과정</strong></p>
            <ul>
                <li>봄식물 관찰하고 세밀화 그리기</li>
                <li>블루베리 한살이 과정</li>
                <li>블루베리 관찰, 블루베리 묘목 심어보기</li>
                <li>블루베리를 통한 요리 경연대회(바른 먹거리 교육)</li>
                <li>덩이뿌리와 덩이줄기 알아보기(고구마, 감자캐기)</li>
                <li>쌍떡잎 식물 찾아보기</li>
                <li>김치 담궈보기</li>
            </ul>
        </div>
        <div class="col-md-4 col-sm-12 col-xs-12 home-program">
            <h4 class="page-header">기타 세부사항</h4>

            <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-3"><strong>연계교과</strong></div>
                <div class="col-md-9 col-sm-9 col-xs-9 text-justify">초등학교 교과 연계<br>유치원 표준 보육과정 연계<br>(교육 활동 내용에 따라 연계되는
                    교과 변경됨)
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-3"><strong>대상</strong></div>
                <div class="col-md-9 col-sm-9 col-xs-9">유치원, 초등학교, 가족</div>
            </div>
            <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-3"><strong>기간</strong></div>
                <div class="col-md-9 col-sm-9 col-xs-9">연중 (매주 일요일은 휴무)</div>
            </div>
            <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-3"><strong>수용인원</strong></div>
                <div class="col-md-9 col-sm-9 col-xs-9">당일 120명</div>
            </div>
            <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-3"><strong>식사</strong></div>
                <div class="col-md-9 col-sm-9 col-xs-9">가능</div>
            </div>
            <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-3"><strong>숙박</strong></div>
                <div class="col-md-9 col-sm-9 col-xs-9">가족 단위 가능</div>
            </div>
            <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-3"><strong>전화 상담</strong></div>
                <div class="col-md-9 col-sm-9 col-xs-9"><a href="tel:010-7322-8214">010-7322-8214</a></div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <h2 class="page-header">찾아 오시는 길 <a href="http://m.map.naver.com/siteview.nhn?resultCode=12854121"
                                                target="_blank">
                <button type="button" class="btn btn-primary btn-sm">네이버 지도</button>
            </a></h2>
        </div>
        <div class="col-md-12 col-sm-12 col-xs-12">
            <img class="img-responsive" src="${cdn}/files/home/location.gif"/>
        </div>
    </div>
</div>
<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>
<script src="/resources/js/blueimp-gallery.min.js"></script>
<script>
    $(document).ready(function () {
        document.getElementById('home-photos').onclick = function (event) {
            event = event || window.event;
            var target = event.target || event.srcElement,
                    link = target.src ? target.parentNode : target,
                    options = {index: link, event: event},
                    links = this.getElementsByTagName('a');
            blueimp.Gallery(links, options);
        }
    });
</script>