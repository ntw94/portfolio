package project.forums.web.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.forums.domain.comment.Comment;
import project.forums.domain.comment.CommentMapper;
import project.forums.web.comment.form.CommentReplyForm;
import project.forums.web.comment.form.CommentUpdateForm;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    public List<Comment> getList(Integer postId){
        List<Comment>list = commentMapper.getListAll(postId);
        return list;
    }

    public void commentWrite(Comment comment){
        commentMapper.setInsert(comment);
    }
    public void commentDelete(Integer commId, String memberId){

        Comment comment = new Comment();
        comment.setId(commId);
        comment.setCommentWriter(memberId);

        commentMapper.setDelete(comment);
    }
    public void commentUpdate(CommentUpdateForm form){

        Comment updateForm = commentMapper.getListOne(form.getId());

        updateForm.setCommentContent(form.getCommentContent());

        commentMapper.setUpdate(updateForm);
    }

    public void commentReply(CommentReplyForm form){

        //1. 조회해서 부모의 값을 찾는다.
        Comment pComment = commentMapper.getListOne(form.getParentId());

        //2. 자식의 값을 셋팅한다.
        Comment cComment = new Comment();
        cComment.setCommentContent(form.getCommentReplyContent()); // 댓글 내용
        cComment.setCommentWriter(form.getMemberId()); // 현재 작성자
        cComment.setPostId(form.getPostId()); // 현재 글 정보
        cComment.setCommentParentNo(pComment.getCommentParentNo()); // 부모글
        cComment.setCommentSequence(pComment.getCommentSequence()+1);
        cComment.setCommentLevel(pComment.getCommentLevel()+1);
        cComment.setBoardUri(pComment.getBoardUri());

        commentMapper.setReplyInsert(cComment);
        commentMapper.setReplyProcess(cComment);
        //update하면 끝
    }

    public int getTodayComments(String boarUri){
        return commentMapper.getTodayComments(boarUri);
    }
}
