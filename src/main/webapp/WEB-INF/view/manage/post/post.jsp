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
    <h1>게시글 관리</h1>
    <h3><a href="/manage/post/${boardUri}">아이디 검색 / 글 차단 여부</a></h3>
    <h3><a href="/manage/post/deleted/post/${boardUri}">삭제한 글보기</a></h3>
    <h3><a href="/manage/post/deleted/comment/${boardUri}">삭제한 댓글</a></h3>

    <form name="searchForm">
        <select name="searchMenu">
            <option value="id">아이디</option>
            <option value="title">글제목</option>
            <option value="id_title">아이디 + 글 제목</option>
            <option value="id+title_content">아이디 + 글 제목 + 내용</option>
        </select>

        <input type="text" name="keyword" value="${keyword}">
        <button>검색</button>
    </form>


    <form id="postForm" action="/manage/post/${boardUri}/delete" method="post">
        <input type="hidden" name="keyword" value="${keyword}">

        <table>
            <tr>
                <td><input type="checkbox" name="selectall" value="selectall" onclick="selectAll(this)">전체선택 </td>
                <td>번호</td>
                <td>카테고리</td>
                <td>글제목</td>
                <td>글작성시간</td>
                <td>삭제</td>
            </tr>
            <c:forEach var="list" items="${list}">
                <tr>
                    <td><input type="checkbox" name="chkPostId" value="${list.id}" onclick="checkSelectAll()"></td>
                    <td>${list.id}</td>
                    <td>${list.postCategory}</td>
                    <td>${list.postTitle}</td>
                    <td>${list.postRegiDate}</td>
                    <td><input type="button" onclick="deleteBtn('${list.id}')" value='삭제'></td>
                </tr>
            </c:forEach>
        </table>

        <input type="button" onclick="deleteAllBtn()" value="삭제"/>
        <input type="submit"  value="테스트"/>
    </form>


    <ul class="pagination justify-content-center">
        <c:if test="${page.showPrev}">
            <li class="page-item"><a class="page-link" href="/manage/post/${boardUri}?page=${page.beginPage-1}&keyword=${keyword}">Previous</a></li>
        </c:if>
        <c:forEach var="i" begin="${page.beginPage}" end="${page.endPage}">
            <li class="page-item"><a class="page-link" href="/manage/post/${boardUri}?page=${i}&keyword=${keyword}">${i}</a></li>
        </c:forEach>
        <c:if test="${page.showNext}">
            <li class="page-item"><a class="page-link" href="/manage/post/${boardUri}?page=${page.endPage+1}&keyword=${keyword}">Next</a></li>
        </c:if>
    </ul>

    <form>
        <select name="searchMenu">
            <option value="id">아이디</option>
            <option value="title">글제목</option>
            <option value="id_title">아이디 + 글 제목</option>
            <option value="id+title_content">아이디 + 글 제목 + 내용</option>
        </select>

        <input type="text" name="keyword" value="${keyword}">
        <button>검색</button>
    </form>


</div>
</body>
</html>

<script>
    function selectAll(selectAll)  {
        const checkboxes
            = document.getElementsByName('chkPost');

        checkboxes.forEach((checkbox) => {
            checkbox.checked = selectAll.checked;
        })
    }
    function checkSelectAll()  {
        const checkboxes = document.querySelectorAll('input[name="chkPost"]');
        const checked = document.querySelectorAll('input[name="chkPost"]:checked');
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
        form.action='/manage/post/popup/${boardUri}/delete'
        form.method="post";
        form.target=windowTarget;
        form.submit();
    }


</script>