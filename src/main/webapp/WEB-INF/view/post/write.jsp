<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <td>제목 <input type="text" name="postTitle" ></td>
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

<script type="text/javascript">
    var pText;

    $(document).ready(function(){
        commentLoadList();
    });

    function boardDelete(){
        if(confirm("삭제하시겠습니까?")){
            document.deleteForm.action="${contextPath}/boardDelete";
            document.deleteForm.method="post";
            document.deleteForm.submit();
        }
    }

    function commentLoadList(){
        $.ajax({
            url:"comment/listAll/${boardDTO.board_no}",
            type:"get",
            dataType:"json",
            success:commentMakeView,
            error:function(){alert("error");}
        });
    }

    function commentMakeView(data){
        var listHtml="";

        $.each(data,function(index,obj){
            listHtml +="<table class='table' style='border: 1px solid #eeeeee'>";
            listHtml +=	"<tr style='background-color:#eeeeee'>";
            listHtml +=		"<td>";
            listHtml +=			"작성자 &nbsp;"+obj.member_id;
            listHtml +=			"<span class='pull-right'>"+obj.comment_regiDate+"</span>";
            listHtml +=		"</td>";
            listHtml +=	"</tr>";
            listHtml +=	"<tr>";
            listHtml +=		"<td style='height:60px'>";
            listHtml +=			"<textarea id='noraltt"+obj.comment_no+"' style='width:100%;background-color:white;resize:none;border:none'  rows='5' readonly  >"+obj.comment_content+"</textarea>";
            listHtml +=			"<textarea id='updatett"+obj.comment_no+"' style='width:100%;background-color:white;resize:none;border:none;display:none'  rows='5'   >"+obj.comment_content+"</textarea>";
            listHtml +=		"</td>";


            if(obj.member_id == '${member.member_id}'){
                listHtml +=	"<tr>";
                listHtml +=			"<td colspan=2 style='border-top:none'>";
                listHtml +=				"<div id='normalForm"+obj.comment_no+"'>";
                listHtml +=					"<button id=''type='button' class='btn btn-sm pull-right' onclick='commentUpdateForm("+obj.comment_no+")'>수정</button>";
                listHtml +=					"<button type='button' class='btn btn-sm pull-right' onclick='commentDelete("+obj.comment_no+")'>삭제</button>";
                listHtml +=				"</div>";
                listHtml +=				"<div id='updateForm"+obj.comment_no+"' style='display:none'>";
                listHtml +=					"<button id=''type='button' class='btn btn-sm pull-right' onclick='commentUpdate("+obj.comment_no+");'>수정</button>";
                listHtml +=					"<button type='button' class='btn btn-sm pull-right' onclick=resetUpdateForm("+obj.comment_no+");>취소</button>";
                listHtml +=				"</div>";
                listHtml +=			"</td>";
                listHtml +=	"</tr>";
            }
            listHtml +=	"</table>";
        });

        $("#commentView").html(listHtml);

    }

    function commentInsert(){

        if(confirm("글 등록하시겠습니까?")){

            var fData=$("#commentForm").serialize();

            $.ajax({
                url :"comment/insert",
                type: "post",
                data:fData,
                success:commentLoadList,
                error:function(){alert("댓글 작성 오류");}
            });
            $("#comment_content").val("");
        }

    }

    function commentDelete(comment_no){

        if(confirm("글 삭제하시겠습니까?")){

            $.ajax({
                url:"comment/delete",
                type:"get",
                data:{
                    "comment_no":comment_no,
                    "member_id":"${member.member_id}"},
                success:commentLoadList,
                error:function(){alert("댓글 삭제 오류");}
            });
        }
    }

    function commentUpdateForm(comment_no){
        // 1. 댓글 다시 입력할 수 있는 상태로 만든다.
        $("#noraltt"+comment_no).css("display","none");
        $("#updatett"+comment_no).css("display","block");

        $("#updateForm"+comment_no).css("display","block"); // 버튼 활성화
        $("#normalForm"+comment_no).css("display","none");
    }

    function resetUpdateForm(comment_no){

        $("#noraltt"+comment_no).css("display","block");
        $("#updatett"+comment_no).css("display","none");

        $("#updateForm"+comment_no).css("display","none"); // 버튼 활성화
        $("#normalForm"+comment_no).css("display","block");
    }

    function commentUpdate(comment_no){
        var content1 = $("#updatett"+comment_no).val();
        var member_id1 = "${member.member_id}";

        $.ajax({
            url : "comment/update",
            type : "put",
            contentType:'application/json;charset=utf-8',
            data : JSON.stringify({
                "comment_no":comment_no,
                "member_id": member_id1,
                "comment_content":content1
            }),
            success:commentLoadList,
            error:function(){alert("댓글 업데이트 실패");}
        });

    }

    function rateUp(data){
        if(data === ""){
            location.href="${contextPath}/memberLoginForm.do";
        }else{
            var color = $("#rateUpBtn").css("background-color");


            if(color.toString() ==="rgb(255, 255, 255)"){//좋아요 등록
                var fData=$("#recoUpForm").serialize();
                $.ajax({
                    url :"board/reco/insert",
                    type: "post",
                    data:fData,
                    success:function(){
                        $("#rateUpBtn").css("background-color","rgb(255, 255, 0)"); //노란색
                    },
                    error:function(){alert("");}
                });
            }else{ //좋아요 해제
                var fData=$("#recoDownForm").serialize();
                $.ajax({
                    url :"board/reco/delete",
                    type: "post",
                    data:fData,
                    success:function(){
                        $("#rateUpBtn").css("background-color","rgb(255, 255, 255)"); //하얀색
                    },
                    error:function(){alert("");}
                });
            }
        }
    }

</script>


</body>
</html>