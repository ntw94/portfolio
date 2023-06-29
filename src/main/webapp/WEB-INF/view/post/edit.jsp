<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

<form action="/members/${member.memberId}/edit" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>
                <c:if test="${empty profileImg}">
                    <img src="/img/default_img.jpg" />
                </c:if>
                <c:if test="${not empty profileImg}">
                    <img style="width:200px;height:200px" src="/members/images/${member.memberId}">
                </c:if>
            </td>
        </tr>
        <tr>
            <td>
                    첨부파일: <input type="file" name="attachFile">
            </td>
        </tr>
        <tr>
            <td>아이디: <input type="text" name="memberId"  value="${member.memberId}" readonly></td>
        </tr>
        <tr>
            <td>이름: <input type="text" name="memberName"  value="${member.memberName}" ></td>
        </tr>
        <tr>
            <td>연락처: <input type="text" name="memberPhone"  value="${member.memberPhone}" ></td>
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