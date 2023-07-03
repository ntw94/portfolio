<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="../common/top-menu.jsp"/>


<table border="1" width="500px">
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
        <td>${post.postRegiDate}</td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="button" onclick="location.href='/boards/${boardUri}/${post.id}/edit';" value="수정">
            <form action="/boards/${boardUri}/${post.id}/delete" method="post">
                <input type="submit" value="삭제">
            </form>
            <input type="button" onclick="location.href='/boards';" value="목록">
        </td>
    </tr>
</table>

    <form id="commentForm">
        <input type="hidden" name="memberId" value="${member.memberId}"/>
        <input type="hidden" name="postId" value="${post.id }"/>

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
                    <button type="button" class="btn-default btn-sm pull-right" onclick="commentInsert();">댓글 작성</button>
                </td>
            </table>
        </div>
    </form>




</body>
</html>