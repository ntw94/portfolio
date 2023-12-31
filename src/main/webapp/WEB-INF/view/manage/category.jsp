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
    <link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" ></script>

</head>

<style>

    #sortable li{
        font-size: 20px;
        width:50px;
    }
    #sortable li:hover {
        cursor: pointer;
    }
    .itemBoxHighlight {
        border:solid 1px black;
        width:50px;
        height:50px;
        background-color:#eee;
    }
</style>
<body>
<div class="container">
<jsp:include page="../common/manage-top-menu.jsp"/>
    <h1>카테고리 관리</h1>

    <form action="/manage/category/${boardUri}/add" method="post">
    <table class="table">
        <tr>
            <td>카테고리 이름</td>
        </tr>
            <tr>
                <td>
                    <div style="white-space:nowrap; overflow:auto;  width:100%;">
                        <ul style="list-style: none;" id="sortable">
                            <c:forEach var="list" items="${list}">
                            <div id="delete-area-${list.id}"></div>

                            <li class="ui-state-default" id="cate-${list.id}">
                                <input type="hidden" name='categoryOrder' value="${list.categoryOrder}">
                                <div>
                                    <input type="hidden" name='cateId' id="cate-id-${list.id}" value="${list.id}">
                                    <input style="margin-left: 50px;" type="text" name='categoryName' id="cate-menu-${list.id}"  value="${list.categoryMenu}" >
                                    <input type="button" value="수정" onclick="updateBtn('${list.id}')" />
                                    <input type="button" value="삭제" onclick="deleteBtn('${list.id}')" />
                                </div>
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                </td>
            </tr>
    </table>

        <button >저장</button>
    </form>

    카테고리 추가: <input type="text" id="inputCategoryData" value="">
    <input type="button" onclick="cateAdd()" value="추가">


<script>
    //$("#sortable").sortable();
    //$("#sortable").disableSelection(); // 아이템 내부의 글자를 드래그 해서 선택하지 못하도록 하는 기능
    var newCategoryId = 1;

    function updateBtn(id){
        // const updateInput = document.getElementById('cate-menu-'+id);
        // updateInput.readOnly = false;
        //
        // var jsHtml = "";
        // jsHtml += "<input type='hidden' name='updateCategoryId' id='update-cate-"+id+"' value=''>"
        //
        // const deleteArea = document.getElementById('cate-id-'+id);
        // deleteArea.insertAdjacentHTML("afterend", jsHtml);
        //
        // const updateCheck = document.getElementById('update-cate-'+id).value;
        // alert(updateCheck);
        // if(updateCheck !== '') {
        //     return;
        // }
        // updateCheck.value = id;


        reorder()
    }

    function deleteBtn(id){

        var jsHtml = "";
        jsHtml += "<input type='hidden' name='deleteCategoryId' id='delete-cate-"+id+"' value="+id+">"

        const deleteArea = document.getElementById('delete-area-'+id);
        deleteArea.insertAdjacentHTML("afterbegin", jsHtml);

        //li삭제
        const item = document.getElementById("cate-"+id);
        item.remove();

        reorder();
    }

    function newCategoryDelete(id){
        const item = document.getElementById("new-cate-"+id);
        item.remove();

        reorder();
    }

    //새로 deleteAddBtn, updateAddBtn만들어서 관리
    function cateAdd(){

        var ulNode = document.getElementById("sortable");
        var data = $("#inputCategoryData").val();
        var jsHtml = "";

        jsHtml +=   "<input type='hidden' name='newCategoryOrder' value=''>";
        jsHtml +=   "<div>";
        jsHtml +=       "<input type='hidden' name='newCateId' value='-1' >";
        jsHtml +=       "<input style='margin-left:50px'; type='text' name='newCategoryName' value='"+data+"'>";
        jsHtml +=       "<input type='button' value='수정' onclick='' >";
        jsHtml +=       "<input type='button' value='삭제' onclick='newCategoryDelete("+newCategoryId+")' >";
        jsHtml +=   "</div>";

        var temp = document.createElement('li');
        temp.setAttribute('class','ui-state-default');
        temp.setAttribute('id','new-cate-'+newCategoryId);
        temp.innerHTML = jsHtml;

        ulNode.append(temp);

        $("#inputCategoryData").val("");

        newCategoryId++;
        reorder();
    }

    $("#sortable").sortable({
        placeholder:"itemBoxHighlight", /* 이동할 위치 css 적용  */
        start:function(event,ui){
            // 드래그 시작 시 호출
        },
        stop:function(event,ui){
            // 드래그 종료 시 호출
            reorder();
        }
    });

    /* 번호 재입력(내부적으로) */
    function reorder() {
        $("#sortable li>input").each(function(i, box) {
            $(box).val(i + 1);
        });
    }
</script>


</div>
</body>
</html>