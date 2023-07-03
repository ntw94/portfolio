package project.forums.web.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.forums.domain.comment.Comment;
import project.forums.domain.comment.CommentMapper;
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
}
