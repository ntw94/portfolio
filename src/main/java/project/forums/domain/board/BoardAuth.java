package project.forums.domain.board;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardAuth {
    private int id;
    private String boardUri;
    private String memberId;
    private BoardRole boardRole;
    private LocalDateTime regiDate;
}
