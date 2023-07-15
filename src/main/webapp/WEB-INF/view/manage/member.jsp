<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../common/_include.jsp" %>

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
    <h1>회원관리</h1>
    <h2>전체 회원 관리</h2>
    <h2>차단 회원 관리</h2>

    <form method="get" action="/manage/member/${boardUri}/search">
        아이디 검색 : <input type="text" name="keyword">
        <input type="date" name="date"/>
        <input type="submit" value="검색">
    </form>


    <table>
        <tr>
            <td>아이디</td>
            <td>가입일</td>
            <td>게시글 수</td>
            <td>댓글 수</td>
            <td>활동여부</td>
        </tr>
        <c:forEach var="list" items="${mList}">
            <tr>
                <td>${list.memberId}</td>
                <td>${list.memberRegiDate}</td>
                <td>미구현</td>
                <td>미구현</td>
                <td>미구현</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>