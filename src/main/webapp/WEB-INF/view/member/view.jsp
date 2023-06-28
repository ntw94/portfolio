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
        <td>${member.id}</td>
    </tr>
    <tr>
        <td>프로필 사진</td>
        <td>
            <img style="width:200px;height:200px" src="/members/images/${member.memberId}">
        </td>
    </tr>
    <tr>
        <td>아이디</td>
        <td>${member.memberId}</td>
    </tr>
    <tr>
        <td>이름</td>
        <td>${member.memberName}</td>
    </tr>
    <tr>
        <td>연락처</td>
        <td>${member.memberPhone}</td>
    </tr>
    <tr>
        <td>등록일</td>
        <td>${member.memberRegiDate}</td>
    </tr>

    <tr>
        <td colspan="2">
            <input type="button" onclick="location.href='/members/${member.memberId}/edit';" value="수정">
            <input type="button" onclick="location.href='/members/${member.memberId}/delete';" value="삭제">
            <input type="button" onclick="location.href='/members';" value="목록">
        </td>
    </tr>
</table>


</body>
</html>