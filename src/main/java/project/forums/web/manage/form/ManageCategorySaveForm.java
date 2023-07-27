package project.forums.web.manage.form;

import lombok.Data;

@Data
public class ManageCategorySaveForm {
    private String[] categoryOrder;
    private String[] categoryName;
    private String boardUri;
}
