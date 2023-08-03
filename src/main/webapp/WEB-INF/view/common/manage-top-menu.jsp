<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="_include.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<%@include file="top-menu.jsp"%>

    <ul style="display:flex; list-style: none ">
        <li style="margin-right: 20px;"><a class="nav-link active"  href="/manage/home/${boardUri}">홈</a></li>
        <li style="margin-right: 20px;"><a class="nav-link" href="/manage/member/${boardUri}">회원관리</a></li>
        <li style="margin-right: 20px;"><a class="nav-link" href="/manage/post/${boardUri}">글관리</a></li>
        <li style="margin-right: 20px;"><a class="nav-link" href="/manage/category/${boardUri}">카테고리 관리</a></li>
        <li style="margin-right: 20px;"><a class="nav-link" href="/manage/boards/${boardUri}">게시판 설정</a></li>
    </ul>

