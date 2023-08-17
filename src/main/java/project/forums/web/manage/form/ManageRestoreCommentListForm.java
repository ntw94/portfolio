package project.forums.web.manage.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManageRestoreCommentListForm {
    private String keyword;
    private String searchMenu;

    private int page= 1;
    private int perPageSize = 10;

    private int[] chkCommentId;
    private String boardUri;
}
