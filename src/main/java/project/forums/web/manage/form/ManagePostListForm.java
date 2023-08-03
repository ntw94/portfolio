package project.forums.web.manage.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ManagePostListForm {
    private int id;
    private String boardUri;
    private String postTitle;
    private String postWriter;
    private String postContent;
    private int postHit;
    private String postCategory;
    private int noticeNo;
    private LocalDateTime postRegiDate;
}
