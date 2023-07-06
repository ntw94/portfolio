package project.forums.web.post.form;

import lombok.Data;

@Data
public class PostSaveForm {
    private String postTitle;
    private String postWriter;
    private String postContent;
    private String postCategory;
}
