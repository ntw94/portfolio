package project.forums.domain.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
    private int id;
    private String boardTitle;
    private String boardUri;
    private String boardDescription;
    private String uploadFileName;
    private String storeFileName;
    private String memberId;
    private int boardCategoryMainId;
    private int boardCategorySubId;
    private LocalDateTime boardCreateDate;
    private int score;
}
