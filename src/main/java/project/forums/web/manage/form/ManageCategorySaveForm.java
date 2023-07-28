package project.forums.web.manage.form;

import lombok.Data;

@Data
public class ManageCategorySaveForm {
    private String[] id;
    private String[] categoryOrder;
    private String[] categoryName;
//    private String[] addCategoryName;
//    private String[] addCategoryOrder;
    private String boardUri;
}
