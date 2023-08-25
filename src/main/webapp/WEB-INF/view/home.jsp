
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
    <style>
        *{
            list-style: none;

        }
        a{
            text-decoration: none;
            color: black;
        }

        .cateBtn{
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


<jsp:include page="common/top-menu.jsp"/>
<div class="container">
    <h2 class="m-3"> 활발한 게시판 </h2>
    <div class="d-flex justify-content-center align-content-center flex-wrap">
    <c:forEach var="list" items="${rankList}" step="1">
            <a href="/boards/${list.boardUri}">
                <div class="m-3 row justify-content-center">
                    <img style="width: 250px; height: 150px" src="/file/boards/${list.boardUri}/image" />
                </div>
                <div class="m-3 row justify-content-center">
                        ${list.boardTitle}<br>
                        ${list.boardDescription}<br><br>
                </div>
            </a>
<%--        <div class="row">--%>
<%--            <div class="col">--%>
<%--                --%>
<%--            </div>--%>
<%--&lt;%&ndash;                <div class="col">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <div class="row justify-content-center align-content-center">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <a href="/boards/${rankList[status.index+1].boardUri}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <div class="row justify-content-center">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <img style="width: 350px; height: 250px" src="/file/boards/${rankList[status.index+1].boardUri}/image" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <div class="row justify-content-center">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                ${rankList[status.index+1].boardTitle}<br>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                ${rankList[status.index+1].boardDescription}<br><br>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </div>&ndash;%&gt;--%>
<%--        </div>--%>

    </c:forEach>
    </div>

    <br>
    <h3>게시판 둘러보기</h3>

    <div >
        <div class="d-flex justify-content-center">
        <c:forEach var="list" items="${boardMainCategoryList}">
            <input class="m-3 cateBtn" type="button" onclick="cateClick(${list.id})" value="${list.categoryName}"/>
        </c:forEach>
        </div>
    </div>
    <div>
        <ul id="resultMainCategoryChildArea" class="d-flex flex-wrap">
        </ul>
    </div>
<%--    =========================================--%>
<%--    <table class="table table-bordered">--%>
<%--        <c:forEach var="list" items="${board}">--%>
<%--            <tr>--%>
<%--                <td width="20%">--%>
<%--                    <img style="width:200px;height:200px" src="/file/boards/${list.boardUri}/image">--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <h2>${list.boardTitle}</h2> <br>--%>
<%--                    <a href="/boards/${list.boardUri}">${list.boardUri}</a><br>--%>
<%--                    ${list.boardDescription}<br>--%>
<%--                    게시글<br>--%>
<%--                    게시글<br>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>


</div>

<div style="height: 400px;">

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
                `<li class="m-3">
                     <a href="/boards/`+categoryList[i].boardUri+`">
                        <div class="d-flex justify-content-center align-items-center">
                            <div class="m-2">
                                <img style="width:80px;height:80px" src="/file/boards/`+categoryList[i].boardUri+`/image">
                            </div>
                            <div class="m-2">
                                <div>`+categoryList[i].boardTitle+`</div>
                                <div>`+categoryList[i].boardDescription+`</div>
                                <div>`+categoryList[i].boardSubCategory.categoryName+`<div>
                            </div>
                        </div>
                    </a>
                </li>`
            )
        }

    }
</script>