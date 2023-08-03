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
    <h3><a href="/manage/post/reported/${boardUri}">신고관리</a></h3>
    <h3><a href="/manage/post/deleted/post/${boardUri}">삭제한 글보기</a></h3>
    <h3><a href="/manage/post/deleted/comment/${boardUri}">삭제한 댓글</a></h3>

    <form name="searchForm">
        <select name="searchMenu">
            <option>아이디</option>
            <option>글제목</option>
            <option>아이디 + 글 제목</option>
            <option>아이디 + 글 제목 + 내용</option>
        </select>

        <input type="text" name="keyword">
        <button>검색</button>
    </form>


    <%-- 댓글이랑 글 종류에 따라 표시되는 내용도 다른데 왜 다르지?
           글은 쓰면 안에 있는 내용이 글 내용, 글 제목 이렇게 구성되고
           댓글은 글을 스면 안에 내용밖에 없고 글 제목이 없다. 내용뿐이다.. 그러면 이걸 어떻게?
           아니면 글 내용으로 통하? 아니면 글이면 글제목, 댓글이면 댓글로 할까? 옆에 정렬 버튼 놔두고.. 이렇게 해도 될거 같기도 하고
           형태 1 통합 검색으로 댓글 + 글 형태로 출력
           형태 2 버튼을 통해 댓글은 댓글만 글은 글의 형태만 볼수있게한다?
           그냥 이걸 다하면되지뮤

           그러면 일단 컬럼부터가 문제다 댓글은 있지만 댓글이 아닌건 없다 처음부터 이런 난관에 빠지는데..
           아니면 글은 그대로 댓글은 댓글대로 검색할 수 있께 할까?
           어떻게 할까요~ 애초에 이 페이지가 왜있을까 글 관리ㅏ려고 하는데 따로따로 하기엔 좀.. 그냥 토글키 하나두고
           글/댓글 이렇게 해도될거 같기도?
           토글 방식으로 짜보자 댓글 누르면 댓글보이고 글 누르면 글 보이게

    --%>

    <%-- 토글이면 두개 만들어야겠지? --%>
    <form id="postForm">
        <table>
            <tr>
                <td><input type="checkbox" name="selectall" value="selectall" onclick="selectAll(this)">전체선택 </td>
                <td>번호</td>
                <td>글제목</td>
                <td>글작성시간</td>
                <td>삭제</td>
            </tr>
            <c:forEach var="list" items="${mList}">
                <tr>
                    <td><input type="checkbox" name="chkMember" value="${list.memberId}" onclick="checkSelectAll()"></td>
                    <td>${list.id}</td>
                    <td>${list.postTitle}</td>
                    <td>${list.regiDate}</td>
                    <td>삭제</td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <ul class="pagination justify-content-center">
        <c:if test="${page.showPrev}">
            <li class="page-item"><a class="page-link" href="/manage/post/${boardUri}?p=${page.beginPage-1}&keyword=${keyword}">Previous</a></li>
        </c:if>
        <c:forEach var="i" begin="${page.beginPage}" end="${page.endPage}">
            <li class="page-item"><a class="page-link" href="/manage/post/${boardUri}?p=${i}&keyword=${keyword}">${i}</a></li>
        </c:forEach>
        <c:if test="${page.showNext}">
            <li class="page-item"><a class="page-link" href="/manage/post/${boardUri}?p=${page.endPage+1}&keyword=${keyword}">Next</a></li>
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