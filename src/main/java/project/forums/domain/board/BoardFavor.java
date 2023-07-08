package project.forums.domain.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardFavor {
    private int id;
    private String boardTitle;
    private String boardUri;
    private String boardDescription;
    private String uploadFileName;
    private String storeFileName;
    private String memberId;
    private LocalDateTime boardCreateDate;
    private int favor;
    private LocalDateTime favorRegiDate;
}
