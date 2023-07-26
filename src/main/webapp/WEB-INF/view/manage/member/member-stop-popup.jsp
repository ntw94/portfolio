<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../../common/_include.jsp" %>

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
            <c:forEach var="list" items="${list}">
                <span>${list} &nbsp;&nbsp;</span>
            </c:forEach>
        </div>

        <form id="saveForm">
            <c:forEach var="list" items="${list}">
                <input type="hidden" name="chkMember" value="${list}">
            </c:forEach>

            <input type="radio" name="reason" onclick=func2() value="성인/도박 등 불법광고 및 스팸 활동"/> 성인/도박 등 불법광고 및 스팸 활동<br>
            <input type="radio" name="reason" onclick=func2() value="도배,광고,욕설,비방"/>  도배,광고,욕설,비방<br>
            <input type="radio" name="reason" onclick=func2() value="운영 원칙을 지키지 않은 활동"/> 운영 원칙을 지키지 않은 활동<br>
            <input type="radio" name="reason" id="customOption" onclick="func1()" value="" />
            <%-- 여기서는 스크립트 조작해서 밑의 input값을 위의 value로 옮격야됨--%>
            <input type="text" class="form-control" id="customReason" placeholder="30자 이내로 작성해 주세요" readonly /><br>

            활동 정지 기간
            <select name="period">
                <option value="1">1일</option>
                <option value="3">3일</option>
                <option value="5">5일</option>
                <option value="7">7일</option>
                <option value="99999">무기한</option>
            </select>

            <br>
            <br>
            <input type="button" class="btn-lg" value="정지하기" onclick="save()"/>
            <input type="button" calss="btn-lg" value="취소" onclick="cancel()"/>
        </form>

    </div>
</body>
</html>

<script>
    function save(){
        var customInput = document.getElementById("customOption");
        var customReason = document.getElementById("customReason");

        customInput.value =  customReason.value;

        var formdata = document.getElementById("saveForm");
        formdata.action="/manage/member/stop/popup/${boardUri}/add";
        formdata.method="post";
        formdata.submit();
    }
    function cancel(){
        window.close();
    }

    function func1(){
        var customReason = document.getElementById("customReason");
        customReason.readOnly = false;
    }
    function func2(){
        var customReason = document.getElementById("customReason");
        customReason.value = '';
        customReason.readOnly = true;
    }


</script>
