package project.forums.web.manage.form;

import lombok.Data;

@Data
public class ManagePostForm {
    //일단 글만 검색하자
    private String searchMenu;
    private String keyword;

    private int page= 1;
    private int perPageSize = 10;

    private String boardUri;


}
