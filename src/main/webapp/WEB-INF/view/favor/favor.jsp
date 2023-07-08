
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/_include.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

</head>
<body>

<jsp:include page="../common/top-menu.jsp"/>

<h1>즐겨찾기한 게시판 목록</h1>

<table class="table table-bordered" style="margin-right:10px;margin-left: 10px">
    <c:forEach var="list" items="${list}">
        <tr>
            <td width="20%">
                <img style="width:200px;height:200px" src="/boards/images/${list.boardTitle}">
            </td>
            <td>
                <h2>${list.boardTitle}</h2> <br>
                <a href="/boards/${list.boardUri}">${list.boardUri}</a><br>
                    ${list.boardDescription}<br>
                게시글<br>
                게시글<br>

            </td>
            <td>
                    <button id="btn-favor-${list.id}" type='button' class='btn btn-sm pull-right' onclick="favor('${member.memberId}',${list.id},${list.favor})">★</button>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    function favor(memId,boardId,status){
        var favorStatus = status;
        if(favorStatus == 1){
            favorDelete(memId,boardId);
        }else{
            favorAdd(memId,boardId);
        }
    }
    function favorAdd(memId,boardId){
        $.ajax({
            url:"/boards/favor/"+memId+"/"+boardId+"/add",
            contentType:'application/json;charset=utf-8',
            type:"post",
            data:JSON.stringify({
                "memberId":memId,
                "boardId":boardId
            }),
            success:function(){
                $("#btn-favor-"+boardId).val("gg");
                $("#btn-favor-"+boardId).html("ㅁㅁㅁ");
            },
            error:function(){alert("error");}
        });
    }
    function favorDelete(memId,boardId){
        $.ajax({
            url:"/boards/favor/"+memId+"/"+boardId+"/delete",
            contentType:'application/json;charset=utf-8',
            type:"get",
            success:function(){
                $("#btn-favor-"+boardId).html("ㅁㅁㅁ");
                $("#btn-favor-"+boardId).val("gg");
            },
            error:function(){alert("error");}
        });
    }
</script>

</body>
</html>