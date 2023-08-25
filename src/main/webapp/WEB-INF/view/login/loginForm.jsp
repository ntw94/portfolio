<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<jsp:include page="../common/top-menu.jsp"/>
<div class="container">
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
</div>



</body>
</html>