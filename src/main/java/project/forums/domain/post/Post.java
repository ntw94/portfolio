package project.forums.domain.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private int id;
    private String boardUri;
    private String postTitle;
    private String postWriter;
    private String postContent;
    private int postHit;
    private LocalDateTime postRegiDate;

}
