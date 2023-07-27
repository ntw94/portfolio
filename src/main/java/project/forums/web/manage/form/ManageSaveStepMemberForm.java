package project.forums.web.manage.form;

import lombok.Data;
import project.forums.domain.board.BoardPosition;

@Data
public class ManageSaveStepMemberForm {
    private String boardUri; // uri
    private String memberId; //아이디
    private BoardPosition role;  // 역할
}
