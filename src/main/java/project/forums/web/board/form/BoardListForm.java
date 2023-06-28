package project.forums.web.board.form;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListForm {
    private int id;
    private String boardTitle;
    private String boardUri;
    private String boardDescription;
    private String uploadFileName;
    private String storeFileName;
    private String memberId;
    private LocalDateTime boardCreateDate;
}
