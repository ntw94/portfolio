<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../../common/_include.jsp" %>


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
<jsp:include page="../../common/manage-top-menu.jsp"/>
    <h1>삭제한 댓글 관리 페이지!!</h1>
    <h3><a href="/manage/post/${boardUri}">아이디 검색 / 글 차단 여부</a></h3>
    <h3><a href="/manage/post/deleted/post/${boardUri}">삭제한 글보기</a></h3>
    <h3><a href="/manage/post/deleted/comment/${boardUri}">삭제한 댓글</a></h3>


</div>
</body>
</html>
