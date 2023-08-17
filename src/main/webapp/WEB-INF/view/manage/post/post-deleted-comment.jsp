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
    <h1>삭제한 댓글 관리 페이지!!</h1>
    <h3><a href="/manage/post/${boardUri}">아이디 검색 / 글 차단 여부</a></h3>
    <h3><a href="/manage/post/deleted/post/${boardUri}">삭제한 글보기</a></h3>
    <h3><a href="/manage/post/deleted/comment/${boardUri}">삭제한 댓글</a></h3>

    <form name="searchForm">
        <select name="searchMenu">
            <option>아이디</option>
            <option>글제목</option>
            <option>아이디 + 글 제목</option>
            <option>아이디 + 글 제목 + 내용</option>
        </select>

        <input type="text" name="keyword" value="${keyword}">
        <button>검색</button>
    </form>


    <form id="postForm" action="/manage/post/deleted/comment/${boardUri}/restore" method="post">

        <input type="hidden" name="keyword" value="${keyword}"/>

        <table>
            <tr>
                <td><input type="checkbox" name="selectall" value="selectall" onclick="selectAll(this)">전체선택 </td>
                <td>번호</td>
                <td>쓴 글</td>
                <td>댓글 내용</td>
                <td>작성 시간</td>
                <td>복구</td>
            </tr>
            <c:forEach var="list" items="${list}">
                <tr>
                    <td><input type="checkbox" name="chkCommentId" value="${list.id}" onclick="checkSelectAll()"></td>
                    <td>${list.id}</td>
                    <td>${list.postId}</td>
                    <td>${list.commentContent}</td>
                    <td>${list.commentRegiDate}</td>
                    <td><input id="restore-${list.id}" type="button" onclick="restore(${list.id})" value="복구"></td>
                </tr>
            </c:forEach>
        </table>

        <input type="submit"  value="복구 테스트"/>
    </form>

    <ul class="pagination justify-content-center">
        <c:if test="${page.showPrev}">
            <li class="page-item"><a class="page-link" href="/manage/post/deleted/comment/${boardUri}?page=${page.beginPage-1}&keyword=${keyword}">Previous</a></li>
        </c:if>
        <c:forEach var="i" begin="${page.beginPage}" end="${page.endPage}">
            <li class="page-item"><a class="page-link" href="/manage/post/deleted/comment/${boardUri}?page=${i}&keyword=${keyword}">${i}</a></li>
        </c:forEach>
        <c:if test="${page.showNext}">
            <li class="page-item"><a class="page-link" href="/manage/post/deleted/comment/${boardUri}?page=${page.endPage+1}&keyword=${keyword}">Next</a></li>
        </c:if>
    </ul>

    <form>
        <select name="searchMenu">
            <option>아이디</option>
            <option>글제목</option>
            <option>아이디 + 글 제목</option>
            <option>아이디 + 글 제목 + 내용</option>
        </select>

        <input type="text" name="keyword">
        <button>검색</button>
    </form>

</div>
</body>
</html>




<script>

    function restore(id){
        var form = document.getElementById('postForm');
        form.action="/manage/post/deleted/comment/${boardUri}/restore/"+id;
        form.method = "post";
        form.submit();
    }

    function selectAll(selectAll)  {
        const checkboxes
            = document.getElementsByName('chkCommentId');

        checkboxes.forEach((checkbox) => {
            checkbox.checked = selectAll.checked;
        })
    }

    function checkSelectAll()  {
        const checkboxes = document.querySelectorAll('input[name="chkCommentId"]');
        const checked = document.querySelectorAll('input[name="chkCommentId"]:checked');
        const selectAll = document.querySelector('input[name="selectall"]');

        if(checkboxes.length === checked.length)  {
            selectAll.checked = true;
        }else {
            selectAll.checked = false;
        }
    }

    <%--function openModal(){--%>
    <%--    let popOption = "width=650px, height=750px, top=300px, left=300px, scrollbars=yes";--%>
    <%--    let openUrl = "";--%>
    <%--    let windowTarget = 'pop';--%>
    <%--    window.open(openUrl,windowTarget,popOption);--%>

    <%--    var form = document.getElementById("memberForm")--%>
    <%--    form.action='/manage/member/stop/popup/${boardUri}'--%>
    <%--    form.method="post";--%>
    <%--    form.target=windowTarget;--%>
    <%--    form.submit();--%>
    <%--}--%>

</script>