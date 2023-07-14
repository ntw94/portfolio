<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        .image.image_resized{
            display:block;
            box-sizing: border-box;
        }
        .image.image_resized img{
            width: 100%;
        }
        .image.image_resized > figcaption{
            display: block;
        }
    </style>
</head>
<body>

<jsp:include page="../common/top-menu.jsp"/>


<table border="1" width="100%">
    <tr>
        <td>회원번호</td>
        <td>${post.id}</td>
    </tr>
    <tr>
        <td>작성자</td>
        <td>
            ${post.postWriter}
        </td>
    </tr>
    <tr>
        <td>내용</td>
        <td>${post.postContent}</td>
    </tr>
    <tr>
        <td>등록일</td>
        <td>
            <fmt:parseDate value="${post.postRegiDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both"/>
            <fmt:formatDate pattern="yyyy.MM.dd HH:mm:ss" value="${parsedDateTime}" />
            </td>
    </tr>
    <tr>
        <td>조회수</td>
        <td>${post.postHit}</td>
    </tr>
    <tr>
        <td>추천수</td>
    </tr>

    <tr>
        <td colspan="2">
            <input type="button" onclick="location.href='/boards/${boardUri}/${post.id}/edit';" value="수정">
            <form action="/boards/${boardUri}/${post.id}/delete" method="post">
                <input type="submit" value="삭제">
            </form>
            <input type="button" onclick="location.href='/boards/${boardUri}';" value="목록">
        </td>
    </tr>
</table>

    <form id="commentForm">
        <input type="hidden" name="memberId" value="${member.memberId}"/>
        <input type="hidden" name="postId" value="${post.id }"/>
        <input type="hidden" name="boardUri" value="${boardUri}" />
        <div class="panel-body" >
            <table class="table" >
                <tr>
                    <td >댓글작성&nbsp;&nbsp;${member.memberId }</td>
                </tr>
                <tr>
                    <td width="100%" style="border-top:none;">
                                            <textarea style=" resize:none;"rows="4"
                                                      id="commentContent"
                                                      name="commentContent" class="form-control"
                                                      placeholder="내용을 입력해 주세요"></textarea>
                    </td>
                </tr>
                <td>
                    <button type="button" class="btn-default btn-sm pull-right" onclick="commentWrite();">댓글 작성</button>
                </td>
            </table>
        </div>
    </form>

    <div id="commentReplyContainer">
        <div id="commentReplyForm" style="display:none;">
            <input type="hidden" id="commentReplyBoardUri" name="boardUri" value="${boardUri}"/>
            <input type="hidden" id="commentReplyWriter" name="memberId" value="${member.memberId}"/>
            <input type="hidden" id="commentReplyPostId" name="postId" value="${post.id }"/>
            <input type="hidden" id="commentReplyParentId" value=""/>
            <div class="panel-body" >
                <table class="table" >
                    <tr>
                        <td >댓글작성&nbsp;&nbsp;${member.memberId }</td>
                    </tr>
                    <tr>
                        <td width="100%" style="border-top:none;">
                                                    <textarea style=" resize:none;"rows="4"
                                                              id="commentReplyContent"
                                                              name="commentReplyContent" class="form-control"
                                                              placeholder="댓글을 입력해 주세요"></textarea>
                        </td>
                    </tr>
                    <td>
                        <button type="button" class="btn-default btn-sm pull-right" onclick="commentReplyWrite();">댓글 작성</button>
                    </td>
                </table>
            </div>
        </div>
    </div>

<hr>

<div id="commentView"></div>

<script type="text/javascript">
    var pText;

    $(document).ready(function(){
        commentLoadList();
    });

    function commentLoadList(){
        $("#commentReplyForm").appendTo("#commentReplyContainer");
        $("#commentReplyForm").css("display","none");
        $.ajax({
            url:"/comments/${post.id}",
            type:"get",
            dataType:"json",
            success:commentMakeView,
            error:function(){alert("error");}
        });
    }

    function commentMakeView(data){
        var listHtml="";


        $.each(data,function(index,obj){
            var level = obj.commentLevel;
            var space = (level > 4) ? 150 : level * 20 + 10;

            listHtml +="<table class='table' style='border: 1px solid #eeeeee;margin-left:"+space+"px'>";
            listHtml +=	"<tr style='background-color:#eeeeee'>";
            listHtml +=		"<td>";
            listHtml +=			"작성자 &nbsp;"+obj.commentWriter;
            listHtml +=			"<span class='pull-right'>"+obj.commentRegiDate+"</span>";
            listHtml +=			"<button type='button' class='btn btn-sm pull-right' onclick='commentReplyForm("+obj.id+","+obj.commentLevel+")'>댓글쓰기</button>"
            listHtml +=		"</td>";
            listHtml +=	"</tr>";
            listHtml +=	"<tr>";
            listHtml +=		"<td style='height:60px'>";
            listHtml +=			"<textarea id='noraltt"+obj.id+"' style='width:100%;background-color:white;resize:none;border:none'  rows='5' readonly  >"+obj.commentContent+"</textarea>";
            listHtml +=			"<textarea id='updatett"+obj.id+"' style='width:100%;background-color:white;resize:none;border:none;display:none'  rows='5'   >"+obj.commentContent+"</textarea>";
            listHtml +=		"</td>";

            if(obj.commentWriter == '${member.memberId}'){
                listHtml +=	"<tr>";
                listHtml +=			"<td colspan=2 style='border-top:none'>";
                listHtml +=				"<div id='normalForm"+obj.id+"'>";
                listHtml +=					"<button id=''type='button' class='btn btn-sm pull-right' onclick='commentUpdateForm("+obj.id+")'>수정</button>";
                listHtml +=					"<button type='button' class='btn btn-sm pull-right' onclick='commentDelete("+obj.id+")'>삭제</button>";
                listHtml +=				"</div>";
                listHtml +=				"<div id='updateForm"+obj.id+"' style='display:none'>";
                listHtml +=					"<button id=''type='button' class='btn btn-sm pull-right' onclick='commentUpdate("+obj.id+");'>수정</button>";
                listHtml +=					"<button type='button' class='btn btn-sm pull-right' onclick=resetUpdateForm("+obj.id+");>취소</button>";
                listHtml +=				"</div>";
                listHtml +=			"</td>";
                listHtml +=	"</tr>";
            }

            listHtml += "<tr>";
            listHtml +=     "<td> <div id='comment-reply-box"+obj.id+"'>"+"</div></td>";
            listHtml += "</tr>";
            listHtml +=	"</table>";
        });

        $("#commentView").html(listHtml);

    }

    function commentWrite(){
        if(confirm("댓글 작성하시겠습니까?")){
            const fData = $("#commentForm").serialize();
            $.ajax({
                url :"/comments/${post.id}/write",
                type: "post",
                data:fData,
                success:commentLoadList,
                error:function(){
                    alert("댓글 작성 오류");
                }
            });
            $("#commentContent").val("");
        }
    }

    function commentDelete(id){
        if(confirm("글 삭제하시겠습니까?")){
            $.ajax({
                url:"/comments/${postId}/delete",
                type:"post",
                data:{
                    "id":id,
                    "memberId":"${member.memberId}"
                },
                success:commentLoadList,
                error:function(){alert("댓글 삭제 오류");}
            });
        }
    }

    function commentUpdateForm(id){
        // 1. 댓글 다시 입력할 수 있는 상태로 만든다.
        $("#noraltt"+id).css("display","none");
        $("#updatett"+id).css("display","block");

        $("#updateForm"+id).css("display","block"); // 버튼 활성화
        $("#normalForm"+id).css("display","none");
    }

    function resetUpdateForm(id){
        $("#noraltt"+id).css("display","block");
        $("#updatett"+id).css("display","none");

        $("#updateForm"+id).css("display","none"); // 버튼 활성화
        $("#normalForm"+id).css("display","block");
    }

    function commentUpdate(id){
        var uCommentContent = $("#updatett"+id).val();
        var uMemberId = "${member.memberId}";

        $.ajax({
            url : "/comments/${postId}/edit",
            type : "put",
            contentType:'application/json;charset=utf-8',
            data : JSON.stringify({
                "id":id, // 댓글 고유번호
                "memberId": uMemberId,
                "commentContent":uCommentContent
            }),
            success:commentLoadList,
            error:function(){alert("댓글 업데이트 실패");}
        });
    }

    function commentReplyForm(id,level){
        if($("#commentReplyForm").css('display') == 'none') {
            $("#commentReplyForm").css("display", "block");
        }

        if(level >= 3)
            $("#commentReplyForm").css("width",'70%');
        else
            $("#commentReplyForm").css("width",(100-(level*10))+'%');

        $("#commentReplyContent").val("");
        $("#commentReplyParentId").val(id);
        $("#commentReplyForm").appendTo("#comment-reply-box"+id);
    }
    function commentReplyWrite(){

        var commReplyContent = $("#commentReplyContent").val();
        var commReplyWriter = $("#commentReplyWriter").val();
        var commPostId = $("#commentReplyPostId").val();
        var commReplyParentId = $("#commentReplyParentId").val();
        var commReplyBoardUri = $("#commentReplyBoardUri").val();

        $.ajax({
            url : "/comments/${postId}/reply",
            type : "post",
            contentType:'application/json;charset=utf-8',
            data : JSON.stringify({
                "parentId":commReplyParentId, // 댓글 고유번호
                "memberId": commReplyWriter,
                "commentReplyContent":commReplyContent,
                "postId":commPostId,
                "boardUri":commReplyBoardUri
            }),
            success:commentLoadList,
            error:function(){alert("댓글 업데이트 실패");}
        });
    }


    <%--function rateUp(data){--%>
    <%--    if(data === ""){--%>
    <%--        location.href="${contextPath}/memberLoginForm.do";--%>
    <%--    }else{--%>
    <%--        var color = $("#rateUpBtn").css("background-color");--%>


    <%--        if(color.toString() ==="rgb(255, 255, 255)"){//좋아요 등록--%>
    <%--            var fData=$("#recoUpForm").serialize();--%>
    <%--            $.ajax({--%>
    <%--                url :"board/reco/insert",--%>
    <%--                type: "post",--%>
    <%--                data:fData,--%>
    <%--                success:function(){--%>
    <%--                    $("#rateUpBtn").css("background-color","rgb(255, 255, 0)"); //노란색--%>
    <%--                },--%>
    <%--                error:function(){alert("");}--%>
    <%--            });--%>
    <%--        }else{ //좋아요 해제--%>
    <%--            var fData=$("#recoDownForm").serialize();--%>
    <%--            $.ajax({--%>
    <%--                url :"board/reco/delete",--%>
    <%--                type: "post",--%>
    <%--                data:fData,--%>
    <%--                success:function(){--%>
    <%--                    $("#rateUpBtn").css("background-color","rgb(255, 255, 255)"); //하얀색--%>
    <%--                },--%>
    <%--                error:function(){alert("");}--%>
    <%--            });--%>
    <%--        }--%>
    <%--    }--%>
    <%--}--%>
</script>

</body>
</html>