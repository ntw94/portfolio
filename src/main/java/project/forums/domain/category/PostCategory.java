package project.forums.domain.category;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostCategory {
    private int id;
    private String boardUri;
    private String categoryMenu;
    private int categoryOrder;
    private LocalDateTime categoryRegiDate;
}
