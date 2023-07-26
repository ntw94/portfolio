package project.forums.domain.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardRole {
    private int id;
    private String boardUri;
    private String memberId;
    private BoardPosition boardRole;
    private LocalDateTime regiDate;
}
