package project.forums.web.comment.form;

import lombok.Data;

@Data
public class CommentUpdateForm {
    private int id;
    private int postId; // fk
    private String commentWriter;//작성자
    private String commentContent;//글 내용
    private String commentRegiDate;//등록시간
}
