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
<jsp:include page="../../common/manage-top-menu.jsp"/>
    <h1>회원관리</h1>
    <h2>회원 검색</h2>
    <h2><a href="/manage/member/stop/${boardUri}">차단 회원 관리</a></h2>
    <h2><a href="/manage/member/step/${boardUri}">스탭관리</a></h2>

    <%-- 매니저 부여 --%>
    <form id="saveManageForm" action="/manage/member/step/${boardUri}/add" method="post">
        <hr>
            <select name="role">
                <option value="">--선택--</option>
                <option value="SUB_MANAGER">부 매니저</option>
                <option value="STEP">스탭</option>
            </select>
        <hr>
            아이디 검색 : <input type="text" id="keyword" name="keyword" value="${keyword}">
            <input type="button" value="검색" onclick="manageMemberSearch()">
            <div id="result">

            </div>
        <hr>
    </form>

    <%-- 현재 스탭 현황 --%>
    <form id="stepListForm">
        <input type="hidden" id="stepId" name="memberId" value="">
        <input type="hidden" name="boardUri" value="${boardUri}">
    </form>

        <table>
            <tr>
                <td>역할</td>
                <td>아이디</td>
                <td>부여일</td>
                <td>매니저 해지</td>
            </tr>
            <c:forEach var="list" items="${stepList}">
                <tr>
                    <td>${list.boardRole}</td>
                    <td>${list.memberId}</td>
                    <td>${list.regiDate}</td>
                    <td><input type="button" id="deleteBtn_${list.id}" class="btn--tiny" value="삭제" onclick="deleteRole('${list.id}','${list.memberId}')"></td>
                </tr>
            </c:forEach>
        </table>


</div>
</body>
</html>

<script>
    function deleteRole(id,memberId){
        $("#stepId").attr("value",memberId);
        $("#stepListForm").attr("method","post");
        $("#stepListForm").attr("action","/manage/member/step/${boardUri}/delete").submit();
    }

    /* 아이디 검색시 */
    function manageMemberSearch(){
        var memberId = $("#keyword").val();

        $.ajax({
            url : "/manage/member/step/${boardUri}/search",
            type : "post",
            contentType:'application/json;charset=utf-8',
            data : JSON.stringify({
                "memberId": memberId,
            }),
            success: function(data){
                var search_var = data;
                var jsHtml = "";

                if(search_var === ""){
                    jsHtml += "<p>"+memberId+"는 없는 회원입니다. </p>"
                }else{
                    jsHtml += "<input type='checkbox' name='memberId' value="+memberId+">"+memberId;
                    jsHtml += "<input type='submit' value='저장'>"
                }

                $("#result").html(jsHtml);
            },
            error:function(){alert("값을 가져오지 못했습니다.");}
        });
    }
</script>