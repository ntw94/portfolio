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
<jsp:include page="../common/manage-top-menu.jsp"/>
    <h1>회원관리</h1>
    <h2>회원 검색</h2>
    <h2><a href="/manage/member/stop/${boardUri}">차단 회원 관리</a></h2>

    <form method="get" action="/manage/member/${boardUri}">
        아이디 검색 : <input type="text" name="keyword" value="${keyword}">
        <input type="date" name="date"/>
        <input type="submit" value="검색">
    </form>

    <form id="memberForm">
        <table>
            <tr>
                <td><input type="checkbox" name="selectall" value="selectall" onclick="selectAll(this)">전체선택 </td>
                <td>아이디</td>
                <td>가입일</td>
                <td>게시글 수</td>
                <td>댓글 수</td>
                <td>활동여부</td>
            </tr>
            <c:forEach var="list" items="${mList}">
                <tr>
                    <td><input type="checkbox" name="chkMember" value="${list.memberId}" onclick="checkSelectAll()"></td>
                    <td>${list.memberId}</td>
                    <td>${list.memberRegiDate}</td>
                    <td>${list.totalPosts}</td>
                    <td>${list.totalComments}</td>
                    <td>미구현</td>
                </tr>
            </c:forEach>
        </table>

        <input type="button" value="활동정지" onclick="openModal()">
    </form>

    <ul class="pagination justify-content-center">
        <c:if test="${page.showPrev}">
            <li class="page-item"><a class="page-link" href="/manage/member/${boardUri}?p=${page.beginPage-1}&keyword=${keyword}">Previous</a></li>
        </c:if>
        <c:forEach var="i" begin="${page.beginPage}" end="${page.endPage}">
            <li class="page-item"><a class="page-link" href="/manage/member/${boardUri}?p=${i}&keyword=${keyword}">${i}</a></li>
        </c:forEach>
        <c:if test="${page.showNext}">
            <li class="page-item"><a class="page-link" href="/manage/member/${boardUri}?p=${page.endPage+1}&keyword=${keyword}">Next</a></li>
        </c:if>
    </ul>
</div>
</body>
</html>

<script>
    function selectAll(selectAll)  {
        const checkboxes
            = document.getElementsByName('chkMember');

        checkboxes.forEach((checkbox) => {
            checkbox.checked = selectAll.checked;
        })
    }
    function checkSelectAll()  {
        const checkboxes = document.querySelectorAll('input[name="chkMember"]');
        const checked = document.querySelectorAll('input[name="chkMember"]:checked');
        const selectAll = document.querySelector('input[name="selectall"]');

        if(checkboxes.length === checked.length)  {
            selectAll.checked = true;
        }else {
            selectAll.checked = false;
        }
    }

    function openModal(){
        let popOption = "width=650px, height=750px, top=300px, left=300px, scrollbars=yes";
        let openUrl = "";
        let windowTarget = 'pop';
        window.open(openUrl,windowTarget,popOption);

        var form = document.getElementById("memberForm")
        form.action='/manage/member/stop/popup/${boardUri}'
        form.method="post";
        form.target=windowTarget;
        form.submit();
    }

</script>