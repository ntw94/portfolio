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
    <%@include file="../common/manage-top-menu.jsp"%>

    <h1>게시판 정보 수정</h1>
    <form>

    <table class="table mt-3 pt-3">
        <tr>
            <td>게시판 주소</td>
            <td>${board.boardUri}</td>
        </tr>
        <tr>
            <td>게시판 아이콘</td>
            <td>
                <img style="width:200px;height:200px" src="/file/boards/${board.boardUri}/image">
                <input type="file" name="boardFile">
            </td>
        </tr>
        <tr>
            <td>게시판 이름</td>
            <td><input type="text" name="boardName" value="${board.boardTitle}"></td>
        </tr>
        <tr>
            <td>게시판 소개</td>
            <td>
                <textarea class="form-control" rows="5" id="comment" name="text">
                    ${board.boardDescription}
                </textarea>
            </td>
        </tr>
    </table>
        <button>저장하기</button>
    </form>
</div>
</body>
</html>