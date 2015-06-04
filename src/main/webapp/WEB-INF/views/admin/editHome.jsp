<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="main" class="container">
    <div class="page-header">
        <h2>첫 페이지</h2>

        <p>Last Updated at ${post.published}</p>
    </div>
    <div class="row">
        <div class="col-md-9 col-sm12 col-xs-12">
            <form:form action="/admin/editHome" method="post" id="article-form">
                <div class="row">
                    <div class="col-md-12 col-sm12 col-xs-12 form-group">
                        <label for="home-title">제목</label>
                        <input type="text" name="title" class="form-control input-lg" id="home-title"
                               placeholder="제목을 입력하세요"
                               autofocus="true" value="<c:out value="${post.title}"/>" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-sm12 col-xs-12 form-group">
                        <label for="home-content">내용</label>
                        <textarea name="content" class="form-control input-lg" id="home-content"
                                  placeholder="내용을 입력하세요"
                                  rows="10" required><c:out value="${post.content}"/></textarea>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 col-sm12 col-xs-12 form-group">
                        <form:errors path="*" cssClass="alert alert-danger" element="div"/>
                        <button type="submit" class="btn btn-primary btn-lg">수정</button>
                        <a href="/admin">
                            <button class="btn btn-default btn-lg">취소</button>
                        </a>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>