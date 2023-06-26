<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="_include.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Logo</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse " id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">즐겨찾기</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">검색</a>
                </li>
            </ul>

            <ul class="navbar-nav d-flex">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">그림</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/login">로그인</a></li>
                        <li><a class="dropdown-item" href="#">회원가입</a></li>
                    </ul>
                </li>
            </ul>
            <%-- 로그인 시 될지 안될지 설정 --%>

        </div>
    </div>
</nav>
