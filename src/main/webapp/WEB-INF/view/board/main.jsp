
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
    <jsp:include page="../common/top-menu.jsp"/>
    <h1>여기가 ${board.boardTitle} 게시판입니다.</h1>
    <h2>${board.boardDescription}</h2>
    <h3>만든이: ${board.memberId}</h3>

    <table class="table table-bordered">
        <tr>
            <td>글 번호</td>
            <td>글 제목</td>
            <td>작성자</td>
            <td>조회수</td>
            <td>작성일</td>
        </tr>
        <c:forEach var="list" items="${list}">
        <tr>
            <td>${list.id}</td>
            <td><a href="/boards/${boardUri}/${list.id}">${list.postTitle}</a></td>
            <td>${list.postWriter}</td>
            <td>${list.postHit}</td>
            <td>${list.postRegiDate}</td>
        </tr>
        </c:forEach>
        <tr>
            <td>
                <input type="button" onclick="location.href='/boards/${boardUri}/write';" value="글 추가">
            </td>
        </tr>
    </table>


</div>

</body>
</html>