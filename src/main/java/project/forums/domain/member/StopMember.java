package project.forums.domain.member;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class StopMember {
    private int id;
    private String memberId;
    private String boardUri;
    private LocalDateTime stopDate;
    private LocalDateTime regiDate;

}
