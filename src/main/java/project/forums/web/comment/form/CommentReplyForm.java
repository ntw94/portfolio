package project.forums.web.comment.form;

import lombok.Data;

@Data
public class CommentReplyForm {
    private int parentId;
    private int postId;
    private String memberId;
    private String commentReplyContent;
    private String boardUri;
}
