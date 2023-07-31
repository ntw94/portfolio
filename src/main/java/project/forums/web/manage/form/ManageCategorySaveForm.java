package project.forums.web.manage.form;

import lombok.Data;

import java.util.List;

@Data
public class ManageCategorySaveForm {
    private String[] cateId;
    private String[] categoryOrder;
    private String[] categoryName;
//    private String[] addCategoryName;
//    private String[] addCategoryOrder;
    private List<String> deleteCategoryId;
    private String boardUri;
}
