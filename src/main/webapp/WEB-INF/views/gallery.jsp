<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="gallery-hero" class="edufarm-hero">
    <div class="container">
        <div class="jumbotron">
            <h1>사진 모아보기</h1>

            <h3>수우원 농장에서의 추억들</h3>

            <p><a href="/article/new" class="btn btn-primary btn-lg" role="button">후기 올리기</a></p>
        </div>
    </div>
</div>
<div id="main" class="container">
    <div class="row">
        <div id="gallery" class="col-md-9 col-sm12 col-xs-12">
            <c:forEach var="photo" items="${photoList}"><a href="${photo.uri}"
                                                           title="${photo.user.displayName}님의 사진"><img
                    alt="${photo.user.displayName}님의 사진" src="${photo.thumbnailUri}" width="82px"
                    height="82px"/></a></c:forEach>
        </div>
    </div>
</div>
<div id="blueimp-gallery" class="blueimp-gallery">
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
        document.getElementById('gallery').onclick = function (event) {
            event = event || window.event;
            var target = event.target || event.srcElement,
                    link = target.src ? target.parentNode : target,
                    options = {index: link, event: event},
                    links = this.getElementsByTagName('a');
            blueimp.Gallery(links, options);
        }
    });
</script>