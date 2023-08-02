package project.forums.web.manage.form;

import lombok.Data;

import java.util.List;

@Data
public class ManageCategorySaveForm {

    /* 기존에 있던 카테고리 메뉴들 */
    private Integer[] cateId;
    private String[] categoryOrder;
    private String[] categoryName;

    /*새로 추가된 카테고리 이름*/
    private Integer[] newCateId;
    private String[] newCategoryName;
    private String[] newCategoryOrder;

    private List<Integer> deleteCategoryId;
    private List<Integer> updateCategoryId;
    private String boardUri;

}
