package project.forums.web.board.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Data
public class BoardSaveForm {
    private int id;
    private String boardTitle;
    private String boardUri;
    private String boardDescription;
    private MultipartFile boardImageFile;

    private String memberId;
    private LocalDateTime boardCreateDate;

    private int boardMainCategoryId;
    private int boardSubCategoryId;
}
