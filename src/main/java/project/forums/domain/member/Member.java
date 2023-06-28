package project.forums.domain.member;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
    private int id;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberPhone;
    private LocalDateTime memberRegiDate;
    private Role role;
}
