package project.forums.domain.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardFavor {
    private int id;
    private String memberId;
    private int boardId;
    private int favor;
    private LocalDateTime favorRegiDate;
}
