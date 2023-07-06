package project.forums.web.post.form;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
public class PostListForm {
    private int id;
    private String boardUri;
    private String postTitle;
    private String postWriter;
    private String postContent;
    private int postHit;
    private String postCategory;
    private String postRegiDate;

}
