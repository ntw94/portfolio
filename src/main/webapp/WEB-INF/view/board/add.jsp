<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/header.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form action="/boards/add" method="post" enctype="multipart/form-data">
    <input type="hidden" name="memberId" name= "memberId" value="${member.memberId}" readonly/>
    <table>
        <tr>
            <td>
                게시판 이미지 사진: <input type="file" name="boardImageFile">
            </td>
        </tr>
        <tr>
            <td>게시판 이름:<input type="text" name="boardTitle" ></td>
        </tr>
        <tr>
            <td>게시판 uri: <input type="text" name="boardUri" ></td>
        </tr>
        <tr>
            <td>게시판 설명: <input type="text" name="boardDescription" ></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value ="만들기">
                <input type="button" onclick="location.href='/';" value ="취소">
            </td>
        </tr>
    </table>
</form>



</body>
</html>