<%@ include file="../common/header.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<table border="1" width="500px">
    <tr>
        <td>회원번호</td>
        <td>${post.id}</td>
    </tr>
    <tr>
        <td>작성자</td>
        <td>
            ${post.postWriter}
        </td>
    </tr>
    <tr>
        <td>내용</td>
        <td>${post.postContent}</td>
    </tr>
    <tr>
        <td>등록일</td>
        <td>${post.postRegiDate}</td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="button" onclick="location.href='/boards/${boardUri}/${post.id}/edit';" value="수정">
            <form action="/boards/${boardUri}/${post.id}/delete" method="post">
                <input type="submit" value="삭제">
            </form>
            <input type="button" onclick="location.href='/boards';" value="목록">
        </td>
    </tr>
</table>


</body>
</html>