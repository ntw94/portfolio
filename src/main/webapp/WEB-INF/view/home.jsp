
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

    <c:forEach var="list" items="${boardMainCategoryList}">
       <input type="button" onclick="cateClick(${list.id})" value="${list.categoryName}"/>
    </c:forEach>

    <div>
        <ul id="resultMainCategoryChildArea">
        </ul>
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
    const resultArea = document.getElementById("resultMainCategoryChildArea");
    // 왼쪽으로 스크롤하는 함수
    // $(document).ready(function(){
    //     $("button").click(function(){
    //         alert($("div").scrollLeft() + " px");
    //     });
    // });

    /* ajax 적용해야함 */
    getBoardMainCategories();
    getCategoryList();

    function getBoardMainCategories(){

        fetch('/boards/category/mainCategories')
            .then(response=> response.text())
            .then(result =>console.log(result))
            .catch(reason => console.log(reason + "불러오지 못했습니다." ));
    }

    function cateClick(id){
        fetch('/boards/category/'+id)
            .then(response=> response.json())
            .then(result=> createMainCategoryChild(result))
            .catch(reason => console.log(reason + "불러오지 못했습니다."));
    }

    function getCategoryList(){
        const id = 1;
        fetch('/boards/category/'+id)
            .then(response=> response.json())
            .then(result=> createMainCategoryChild(result))
            .catch(reason => console.log(reason + "불러오지 못했습니다."));
    }

    function createMainCategoryChild(categoryList){
        resultArea.innerHTML = '';

        for (let i = 0; i < categoryList.length; i++) {
            resultArea.insertAdjacentHTML('beforeend',
                `<li>
                    <div>
                        <img style="width:80px;height:80px" src="/file/boards/`+categoryList[i].boardUri+`/image">
                    </div>
                    <div>
                        <div>`+categoryList[i].boardTitle+`</div>
                        <div>`+categoryList[i].boardDescription+`</div>
                        <div>`+categoryList[i].score+`</div>
                    </div>
                </li>`
            )
        }

    }
</script>