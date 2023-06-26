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
    <table>
        <tr>
            <td>아이디: <input type="text" name="memberId" ></td>
        </tr>
        <tr>
            <td>비밀번호: <input type="text" name="memberPwd" ></td>
        </tr>
        <tr>
            <td>비밀번호확인: <input type="text" name="memberPwdChk" ></td>
        </tr>
        <tr>
            <td>이름: <input type="text" name="memberName" ></td>
        </tr>
        <tr>
            <td>연락처: <input type="text" name="memberPhone" ></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value ="제출">
            </td>
        </tr>

    </table>
</form>



</body>
</html>