package project.forums.web.board.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
@Data
public class BoardUpdateForm {
    private int id;
    private String boardTitle;
    private String boardUri;
    private String boardDescription;
    private String uploadFileName;
    private String storeFileName;
    private MultipartFile boardImageFile;

    private String memberId;
    private LocalDateTime boardCreateDate;
}
