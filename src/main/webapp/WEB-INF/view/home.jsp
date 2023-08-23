
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="common/_include.jsp" %>

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
    <jsp:include page="common/top-menu.jsp"/>
    <h1> 활동이 많은 게시판들 </h1>
    =========================================
    <c:forEach var="list" items="${rankList}">
        <div>
            <h3>${status.count}</h3>
            <p>${list.boardTitle}</p>
            <p>${list.boardUri}</p>
            <p>점수: ${list.score}</p>
            <p>카테고리: ${list.mainCategoryId}</p>
            <p>서브: ${list.subCategoryId}</p>
<%--            <img src="/file/boards/${list.boardUri}/image" />--%>
        </div>
    =========================================
    </c:forEach>
    <br>
    =========================================
    게시판 둘러보기<br>
    <%-- 여기가 그거요 그거 --%>
    <c:forEach var="list" items="${boardMainCategoryList}">
        <span>${list.categoryName}&nbsp;&nbsp;</span>
    </c:forEach>
    <div>
        <c:forEach var="list" items="${boardCategoryList}">
            <div>아이디: ${list.id}</div>
            <div>게시판 이름:  ${list.boardTitle}</div>
            <div>게시판 메인 카테고리:  ${list.boardMainCategory.categoryName}</div>
            <div>게시판 서브 카테고리: ${list.boardSubCategory.categoryName}</div>
        </c:forEach>
        <%-- ajax를 이용해서 여기서 리스트형 구현해야함 --%>
    </div>
    =========================================
    <table class="table table-bordered">
        <c:forEach var="list" items="${board}">
            <tr>
                <td width="20%">
                    <img style="width:200px;height:200px" src="/file/boards/${list.boardUri}/image">
                </td>
                <td>
                    <h2>${list.boardTitle}</h2> <br>
                    <a href="/boards/${list.boardUri}">${list.boardUri}</a><br>
                    ${list.boardDescription}<br>
                    게시글<br>
                    게시글<br>
                </td>
            </tr>
        </c:forEach>
    </table>


</div>

</body>
</html>

<script>
    // 왼쪽으로 스크롤하는 함수
    // $(document).ready(function(){
    //     $("button").click(function(){
    //         alert($("div").scrollLeft() + " px");
    //     });
    // });

    /* ajax 적용해야함 */
    getBoardMainCategories();

    function getBoardMainCategories(){

        fetch('/board/mainCategories')
            .then(response=> response.text())
            .then(result =>console.log(result))
            .catch(reason => console.log(reason + "불러오지 못했습니다." ));


    }
</script>