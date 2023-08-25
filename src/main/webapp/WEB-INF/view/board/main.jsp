<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../common/_include.jsp" %>
<style>
    .ck-editor__editable { height: 400px; }
    .ck-content { font-size: 13px; }
</style>



<!DOCTYPE html>
<html lang="ko">
<head>
    <title>forums</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <style>
        *{
            list-style: none;

        }
        a{
            text-decoration: none;
            color: black;
        }

        .btn{
            display: block;
            min-width: 74px;
            height: 40px;
            padding: 0 14px 0;
            border-radius: 4px;
            text-align: center;
            color: #555;
            font-size: 14px;
            box-sizing: border-box;
            border: 1px solid #e5e5e5;
        }
    </style>
</head>
<body>
    <jsp:include page="../common/top-menu.jsp"/>
    <div class="container">
<%--    <h1>--%>
<%--        ${member.memberId} : ${board.memberId}--%>
<%--    </h1>--%>
<%--    <c:if test="${member.memberId eq board.memberId}">--%>
<%--        <a href="/manage/home/${boardUri}/">게시판 관리 </a>--%>
<%--    </c:if>--%>

    <h2>${board.boardTitle}</h2>
    <h3>${board.boardDescription}</h3>

    <a href="/boards/${boardUri}">전체</a> &nbsp;&nbsp;
    <c:forEach var="cList" items="${cateList}">
        <a href="/boards/${boardUri}?category=${cList.categoryMenu}"><span>${cList.categoryMenu}</span></a> &nbsp;&nbsp;
    </c:forEach>


    <table class="table table-bordered">
        <tr>
            <td width="80px">글 번호</td>
            <td>글 제목</td>
            <td>작성자</td>
            <td>조회수</td>
            <td>작성일</td>
        </tr>
        <c:forEach var="list" items="${nList}">
            <tr>
                <td>[공지]${list.id}</td>
                <td>
                    <a href="/boards/${boardUri}/${list.id}">${list.postTitle}</a>
                </td>
                <td>${list.postWriter}</td>
                <td>${list.postHit}</td>
                <td>${list.postRegiDate}</td>
            </tr>
        </c:forEach>
        <c:forEach var="list" items="${list}" varStatus="i">
        <tr>
            <td>${page.pageIdx - i.index}</td>
            <td>
                <c:if test="${!empty list.postCategory }">[${list.postCategory}] </c:if>
                    <a href="/boards/${boardUri}/${list.id}">${list.postTitle}</a>
            </td>
            <td>${list.postWriter}</td>
            <td>${list.postHit}</td>
            <td>${list.postRegiDate}</td>
        </tr>
        </c:forEach>

    </table>
        <div>
            <input type="button" class="btn float-right" onclick="location.href='/boards/${boardUri}/write';" value="글 쓰기">
        </div>

    <ul class="pagination justify-content-center">
        <c:if test="${page.showPrev}">
            <li class="page-item"><a class="page-link" href="/boards/${boardUri}?p=${page.beginPage-1}">Previous</a></li>
        </c:if>
        <c:forEach var="i" begin="${page.beginPage}" end="${page.endPage}">
            <li class="page-item"><a class="page-link" href="/boards/${boardUri}?p=${i}">${i}</a></li>
        </c:forEach>
        <c:if test="${page.showNext}">
            <li class="page-item"><a class="page-link" href="/boards/${boardUri}?p=${page.endPage+1}">Next</a></li>
        </c:if>
    </ul>

<%--    <div>--%>
<%--        <form action="/boards/${boardUri}/search">--%>
<%--            검색:<input type="text" name="keyWord" id="keyWord"> &nbsp;--%>
<%--            <input type="submit" value="검색" >--%>
<%--        </form>--%>
<%--    </div>--%>


</div>

</body>
</html>