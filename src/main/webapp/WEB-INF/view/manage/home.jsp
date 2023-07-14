<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../common/_include.jsp" %>
<style>
    .ck-editor__editable { height: 400px; }
    .ck-content { font-size: 13px; }
</style>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>forums</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
<div class="container">
<jsp:include page="../common/manage-top-menu.jsp"/>
    <h1>게시판 관리 홈</h1>

    <h3>오늘 게시판 현황</h3>
    <ul>
        <li>오늘 쓴 글 수: ${todayPosts} </li>
        <li>오늘 쓴 댓글 수: ${todayComments}</li>
        <li>오늘 신고 건 수: ???</li>
    </ul>

    <h3>게시판 현황</h3>
    <a href="#">더보기</a>
    <ul>
        <li>[공지] 글1~~~~~~~~~~~~~~~~</li>
        <li>[공지] 글2~~~~~~~~~~~~~~~~</li>
        <li>[공지] 글3~~~~~~~~~~~~~~~~</li>
        <li>[공지] 글4~~~~~~~~~~~~~~~~</li>
        <li>[공지] 글5~~~~~~~~~~~~~~~~</li>
    </ul>


</div>
</body>
</html>