package project.forums.web.post.form;

import lombok.Data;

@Data
public class PostUpdateForm {
    private String postTitle;
    private String postWriter;
    private String postContent;
}
