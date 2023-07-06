<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://cdn.ckeditor.com/ckeditor5/38.0.1/classic/ckeditor.js"></script>
</head>
<body>

<form action="/boards/${boardUri}/write" method="post">

    <table>
        <tr>
            <td>
                제목 <input type="text" name="postTitle" >
                말머리 선택:
                <select name="postCategory">
                        <option value="">일반</option>
                    <c:forEach var="list" items="${cateList}">
                        <option value="${list.categoryMenu}">${list.categoryMenu}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                작성자: <input type="text" name="postWriter" id= "postWriter" value="${member.memberId}" readonly/>
            </td>
        </tr>
        <tr>
            <td>
                <textarea style="height: 350px" name="postContent" id="editor">

                </textarea>

            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value ="만들기">
                <input type="button" onclick="location.href='/';" value ="취소">
            </td>
        </tr>
    </table>
</form>

<div name="postContent" id="postContent">

</div>

<script>
    ClassicEditor
        .create(document.querySelector('#editor'))
        .then(editor => {
            console.log('Editor was initialized');
        })
        .catch(error => {
            console.error(error);
        });
</script>



</script>


</body>
</html>