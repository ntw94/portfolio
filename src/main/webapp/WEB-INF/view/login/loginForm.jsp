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

<%--<div>--%>
<%--    <spring:hasBindErrors name="loginForm">--%>
<%--        <c:if test="${errors.hasFieldErrors('loginFail.loginForm,loginFail')}">--%>
<%--            있다.--%>
<%--        </c:if>--%>
<%--        <c:if test="${errors.hasFieldErrors('loginFail.loginForm')}">--%>
<%--            없다.--%>
<%--        </c:if>--%>
<%--    </spring:hasBindErrors>--%>
<%--</div>--%>
<form action="/login?redirectURL=${redirectURL}" method="post">
    <h1>로그인</h1>
        <table>
            <tr>
                <td>아이디: <input type="text" name="loginId" >

                </td>
            </tr>
            <tr>
                <td>비밀번호: <input type="password" name="password" ></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="로그인">
                    <input type="button" onclick="location.href='/members/add';" value="회원가입">
                </td>
            </tr>
        </table>
</form>



</body>
</html>