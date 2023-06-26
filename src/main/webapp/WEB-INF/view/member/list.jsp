
<%@ include file="../common/header.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <table>
        <tr>
            <td>회원번호</td>
            <td>프로필 사진</td>
            <td>아이디</td>
            <td>이름</td>
            <td>연락처</td>
            <td>등록일</td>
        </tr>
        <c:forEach var="list" items="${list}">
            <tr>
                <td>${list.memberNo}</td>
                <td>
                    <img style="width:200px;height:200px" src="/members/images/${list.memberId}">
                </td>
                <td><a href="/members/${list.memberId}">${list.memberId}</a></td>
                <td>${list.memberName}</td>
                <td>${list.memberPhone}</td>
                <td>${list.memberRegiDate}</td>
            </tr>
        </c:forEach>
        <tr>
            <td>
                <input type="button" value="추가" onclick="location.href='/members/add';">
            </td>
        </tr>
    </table>

</body>
</html>