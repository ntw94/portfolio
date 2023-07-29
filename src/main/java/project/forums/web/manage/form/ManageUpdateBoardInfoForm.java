package project.forums.web.manage.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class ManageUpdateBoardInfoForm {
    private String id;
    private String boardTitle;
    private String boardUri;
    private String boardDescription;
    private String uploadFileName;
    private String storeFileName;
    private MultipartFile boardImageFile;

    private String memberId;
    private LocalDateTime boardCreateDate;
}
