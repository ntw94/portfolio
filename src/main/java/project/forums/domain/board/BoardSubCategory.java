package project.forums.domain.board;

import lombok.Data;

@Data
public class BoardSubCategory {
    private int id;
    private int boardMainCategoryId;
    private String categoryName;
}
