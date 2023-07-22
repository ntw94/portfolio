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
        <h2>차단 회원 관리</h2>

        <div style="overflow:auto; width:350px; height:150px;">
            <c:forEach var="list" items="mList">
                <span>아이디 &nbsp;&nbsp;&nbsp;</span>
            </c:forEach>
        </div>

        <form>
            <input type="radio" name="chk_info" value="성인/도박 등 불법광고 및 스팸 활동"> 성인/도박 등 불법광고 및 스팸 활동<br>
            <input type="radio" name="chk_info" value="도배,광고,욕설,비방">  도배,광고,욕설,비방<br>
            <input type="radio" name="chk_info" value="운영 원칙을 지키지 않은 활동"> 운영 원칙을 지키지 않은 활동<br>
            <input type="radio" name="chk_info" value="">
            <%-- 여기서는 스크립트 조작해서 밑의 input값을 위의 value로 옮격야됨--%>
            <input type="text" class="form- control" placeholder="30자 이내로 작성해 주세요"><br>
        </form>

        활동 정지 기간
        <select>
            <option>1일</option>
            <option>3일</option>
            <option>5일</option>
            <option>7일</option>
            <option>무기한</option>
        </select>

        <br>
        <br>
        <br>
        <br>
        <input type="submit">활동정지</input>
        <input type="button">취소</input>
    </div>
</body>
</html>

<script>

</script>
