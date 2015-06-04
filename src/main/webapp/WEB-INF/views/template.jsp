<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@env.getProperty('cdn.host')" var="cdn"/>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- JQuery UI CSS for DatePicker -->
    <link rel="/resources/css/jquery-ui-1.10.4.custom.min.css"/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <!-- Bootstrap Docs -->
    <link rel="stylesheet" href="/resources/css/docs.min.css">
    <!--CSS for JQuery file upload -->
    <link rel="stylesheet" href="/resources/css/jquery.fileupload.css"/>
    <link rel="stylesheet" href="/resources/css/jquery.fileupload-ui.css"/>
    <link rel="stylesheet" href="/resources/css/blueimp-gallery.min.css">

    <!-- Site Custom CSS -->
    <link rel="stylesheet" href="/resources/css/edufarm.common.css"/>

    <!-- Latest JQuery -->
    <script src="/resources/js/jquery-1.11.1.min.js"></script>
    <!-- JQuery UI for DataPicker -->
    <script src="/resources/js/jquery-ui-1.10.4.custom.min.js"></script>
    <!-- JQuery Alter Class plugin -->
    <script src="/resources/js/jquery.alterclass.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="/resources/js/bootstrap.min.js"></script>
    <!-- Site Custom Javascript -->
    <script src="/resources/js/edufarm.common.js"></script>

    <link rel="shortcut icon" href="/resources/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/resources/favicon.ico" type="image/x-icon">

    <title><tiles:getAsString name="title"/></title>
</head>
<body>
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="main"/>
<tiles:insertAttribute name="login"/>
<tiles:insertAttribute name="footer"/>
</body>
</html>
