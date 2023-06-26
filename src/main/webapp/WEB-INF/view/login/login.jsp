<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form action="/members/add" method="post">
    <h1>로그인</h1>
    <table>
        <tr>
            <td>아이디: <input type="text" name="memberId" ></td>
        </tr>
        <tr>
            <td>비밀번호: <input type="text" name="memberPwd" ></td>
        </tr>
    </table>
</form>



</body>
</html>