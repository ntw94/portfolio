package project.forums.domain.comment;

import lombok.Data;

@Data
public class Comment {
    private int id;
    private int postId; // fk
    private String boardUri;
    private String commentWriter;//작성자
    private String commentContent;//글 내용
    private int commentParentNo;
    private int commentSequence;
    private int commentLevel;
    private int commentAvailable;
    private String commentRegiDate;//등록시간
}
