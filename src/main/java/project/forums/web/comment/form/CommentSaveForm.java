package project.forums.web.comment.form;

import lombok.Data;

@Data
public class CommentSaveForm {
    private int postId;
    private String commentWriter;//작성자
    private String commentContent;//글 내용
    private String commentRegiDate;//등록시간
    private String memberId;
}
