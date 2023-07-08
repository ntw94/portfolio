
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/_include.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

</head>
<body>

<jsp:include page="../common/top-menu.jsp"/>

<h1>게시판 검색</h1>

검색: <input type="text">
<table class="table table-bordered">
    <c:forEach var="list" items="${board}">
        <tr>
            <td width="20%">
                <img style="width:200px;height:200px" src="/boards/images/${list.boardTitle}">
            </td>
            <td>
                <h2>${list.boardTitle}</h2> <br>
                <a href="/boards/${list.boardUri}">${list.boardUri}</a><br>
                    ${list.boardDescription}<br>
                게시글<br>
                게시글<br>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>