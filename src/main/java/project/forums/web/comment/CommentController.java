package project.forums.web.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.comment.Comment;
import project.forums.web.comment.form.CommentReplyForm;
import project.forums.web.comment.form.CommentSaveForm;
import project.forums.web.comment.form.CommentUpdateForm;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{postId}")
    public List<Comment> commentList(@PathVariable Integer postId){
        List<Comment> list = commentService.getList(postId);

        log.info("commlist = {}", list);
        return list;
    }
    @PostMapping("/{postId}/write")
    public void commentWrite(@PathVariable Integer postId,@ModelAttribute CommentSaveForm form){

        Comment comment = new Comment();
        comment.setPostId(form.getPostId());
        comment.setCommentContent(form.getCommentContent());
        comment.setCommentWriter(form.getMemberId());
        comment.setCommentRegiDate(form.getCommentRegiDate());

       commentService.commentWrite(comment);

       log.info("writeComment = {}",comment);
    }
    @PostMapping("/{postId}/delete")
    public void commentDelete(@PathVariable Integer postId,
                                       @RequestParam Integer id,
                                       @RequestParam String memberId
                                       ){
        log.info("id={},memberId={}",id,memberId);
        commentService.commentDelete(id,memberId);
    }

    @PutMapping("/{postId}/edit")
    public void commentEdit(@PathVariable Integer postId,
                                     @RequestBody CommentUpdateForm updateForm){
        log.info("commEditForm = {} ",updateForm);
        commentService.commentUpdate(updateForm);
    }

    @PostMapping("/{postId}/reply")
    public void commentReply(
            @PathVariable Integer postId,
            @RequestBody CommentReplyForm commentReplyForm){

        log.info("commitReplyForm = {}", commentReplyForm);
        commentService.commentReply(commentReplyForm);
    }
}
