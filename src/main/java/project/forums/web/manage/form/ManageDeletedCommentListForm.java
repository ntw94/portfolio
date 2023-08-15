package project.forums.web.manage.form;

import lombok.Data;

@Data
public class ManageDeletedCommentListForm {
    private String keyword;
    private String searchMenu;

    private int page= 1;
    private int perPageSize = 10;

    private int[] chkPostId;
    private String boardUri;
}
