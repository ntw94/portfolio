<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>

<jsp:include page="../common/top-menu.jsp"/>
<div class="container">
    <form action="/boards/add" method="post" enctype="multipart/form-data">
        <input type="hidden" name="memberId" name="memberId" value="${member.memberId}" readonly/>
        <table>
            <tr>
                <td>
                    게시판 이미지 사진등록: <input type="file" name="boardImageFile">
                </td>
            </tr>
            <tr>
                <td>게시판 이름:<input type="text" name="boardTitle" ></td>
            </tr>
            <tr>
                <td>게시판 uri: <input type="text" id="boardUri" name="boardUri" >
                    <span>
                        <input type="button" onclick="checkUri()" value="중복확인"/><br>
                        <span id="uriCheckResult"></span>
                    </span>
                </td>
            </tr>
            <tr>
                <td>게시판 카테고리 선택 :
                    <select name="boardMainCategoryId" id="mainCategoryBox" onchange="categoryChange()">
                        <option value="">대분류 선택</option>
                        <c:forEach var="list" items="${mainCategoryList}" >
                            <option value="${list.id}">
                                ${list.categoryName}
                            </option>
                        </c:forEach>
                    </select>
                    <select name="boardSubCategoryId" id="subCategoryBox">
                        <option value="">소분류 선택</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>게시판 설명: <input type="text" name="boardDescription" ></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value ="만들기">
                    <input type="button" onclick="location.href='/';" value ="취소">
                </td>
            </tr>
        </table>
    </form>
</div>


</body>
</html>

<script>
    var subCateList = new Array();
    <c:forEach items="${subCategoryList}" var="item">
    subCateList.push({
        id : "${item.id}",
        boardMainCategoryId: "${item.boardMainCategoryId}",
        categoryName: "${item.categoryName}"
    })
    </c:forEach>


    function categoryChange(){
        var mainSelectBox = document.getElementById("mainCategoryBox");
        var subSelectBox = document.getElementById("subCategoryBox");

        subSelectBox.length = 0;


        var initOption = document.createElement('option');
        initOption.text = '소분류 선택'
        subSelectBox.append(initOption);
        for (let i = 0; i < subCateList.length ; i++) {
            if(mainSelectBox.value === subCateList[i].boardMainCategoryId){
                var option = document.createElement('option');
                option.value=subCateList[i].id;
                option.text=subCateList[i].categoryName;
                subSelectBox.append(option);
            }
        }
    }

    function checkUri(){
        const boardUriInput = document.getElementById('boardUri');
        let message = '';
        const result = document.getElementById('uriCheckResult');

        fetch('/boards/uri/check?boardUri='+boardUriInput.value)
            .then(response => response.text())
            .then(data => {result.innerText = data})
            .catch(reason => alert(reason))


    }

</script>