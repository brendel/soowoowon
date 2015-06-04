<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="main" class="container">
    <div class="page-header">
        <h2>후기 게시판</h2>
    </div>
    <div class="row">
        <div class="col-md-9 col-sm12 col-xs-12">
            <form id="article-form">
                <div class="row">
                    <div class="col-md-12 col-sm12 col-xs-12 form-group">
                        <label for="article-title">글 제목</label>
                        <input type="text" name="title" class="form-control input-lg" id="article-title"
                               placeholder="글 제목을 입력하세요"
                               autofocus="true" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-sm12 col-xs-12 form-group">
                        <label for="article-content">글 내용</label>
                        <textarea name="content" class="form-control input-lg" id="article-content"
                                  placeholder="글 내용을 입력하세요"
                                  rows="10" required></textarea>
                    </div>
                </div>
            </form>
            <form id="fileupload" method="POST"
                  enctype="multipart/form-data">
                <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
                <div class="row fileupload-buttonbar">
                    <div class="col-md-2 col-sm-2 col-xs-12">
                        <!-- The fileinput-button span is used to style the file input field as button -->
                        <span class="btn btn-success fileinput-button">
                            <span class="glyphicon glyphicon-plus"></span> 사진
                            <input type="file" name="photo"/>
                        </span>
                        <!-- The global file processing reservationState -->
                        <span class="fileupload-process"></span>
                    </div>
                    <!-- The global progress reservationState -->
                    <div id="photo-upload-progress" class="col-md-10 col-sm-10 col-xs-12 fileupload-progress fade">
                        <!-- The global progress bar -->
                        <div class="progress progress-striped active" role="progressbar" aria-valuemin="0"
                             aria-valuemax="100">
                            <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                        </div>
                        <!-- The extended global progress reservationState -->
                        <small class="progress-extended">&nbsp;</small>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-sm12 col-xs-12">
                        <ul id="uploaded-photos" class="list-group files"></ul>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="col-md-12 col-sm12 col-xs-12 form-group">
                    <button id="article-submit" type="submit" class="btn btn-primary btn-lg">글쓰기</button>
                    <button id="article-disabled" class="btn btn-primary btn-lg disabled hidden">글쓰기</button>
                    <a href="/article">
                        <button type="button" class="btn btn-default btn-lg">취소</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- The template to display files available for upload -->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
<li class="list-group-item template-upload">
    <div class="row">
        <div class="col-md-2 col-sm-2 col-xs-3">
            <span class="preview">&nbsp;</span>
        </div>
        <div class="col-md-10 col-sm-10 col-xs-9"><small>&nbsp;</small></div>
        <div class="col-md-6 col-sm-5 col-xs-3">
            <p class="name"><small>{%=file.name%}</small></p>
            <strong class="error text-danger"></strong>
        </div>
        <div class="col-md-2 col-sm-3 col-xs-3 text-right">
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
            <small class="size">Processing...</small>
        </div>
        {% if (!i && !o.options.autoUpload) { %}
        <div class="col-md-2 col-sm-2 col-xs-2">
            <button class="btn btn-primary start" disabled>
                <span class="glyphicon glyphicon-upload"></span>
            </button>
        </div>
        {% } %}
        {% if (!i) { %}
        <div class="col-md-2 col-sm-2 col-xs-3 text-right">
            <button class="btn btn-sm btn-warning cancel">
                <span class="glyphicon glyphicon-ban-circle"></span> 취소
            </button>
        </div>
        {% } %}
    </div>
</li>
{% } %}


</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
<li class="list-group-item template-download fade">
    <div class="row">
        <div class="col-md-2 col-sm-2 col-xs-3">
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img width="80px" height="80px"  class="img-responsive img-rounded" src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </div>
        <div class="col-md-10 col-sm-10 col-xs-9"><small>&nbsp;</small></div>
        <div class="col-md-6 col-sm-6 col-xs-4">
            <p class="name">
                {% if (file.url) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}><small>{%=file.name%}</small></a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </div>
        <div class="col-md-2 col-sm-2 col-xs-2 text-right">
            <small><span class="size">{%=o.formatFileSize(file.size)%}</span></small>
        </div>
        <div class="col-md-2 col-sm-2 col-xs-3 text-right">
            <button class="btn btn-sm btn-danger delete" data-photoId="{%=file.photoId%}" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                <span class="glyphicon glyphicon-trash"></span> 삭제
            </button>
        </div>
    </div>
</li>
{% } %}


</script>
<script src="/resources/js/jquery.fileupload/tmpl.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="/resources/js/jquery.fileupload/load-image.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="/resources/js/jquery.fileupload/canvas-to-blob.min.js"></script>
<script src="/resources/js/jquery.ui.widget.js"></script>
<script src="/resources/js/jquery.fileupload/jquery.iframe-transport.js"></script>
<script src="/resources/js/jquery.fileupload/jquery.fileupload.js"></script>
<script src="/resources/js/jquery.fileupload/jquery.fileupload-ui.js"></script>
<script src="/resources/js/jquery.fileupload/jquery.fileupload-process.js"></script>
<script src="/resources/js/jquery.fileupload/jquery.fileupload-validate.js"></script>
<script src="/resources/js/jquery.fileupload/jquery.fileupload-image.js"></script>
<script src="/resources/js/article.js"></script>
