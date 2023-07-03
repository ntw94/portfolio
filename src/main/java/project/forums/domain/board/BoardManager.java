package project.forums.domain.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardManager {
    private int id;
    private String memberId ;
    private String boardUri;
    private BoardRole boardRole;
    private LocalDateTime regiDate;
}
