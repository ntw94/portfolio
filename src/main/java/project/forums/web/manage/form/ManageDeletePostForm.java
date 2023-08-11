package project.forums.web.manage.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManageDeletePostForm {
    private int[] chkPostId;
    private String[] boardUri;
}