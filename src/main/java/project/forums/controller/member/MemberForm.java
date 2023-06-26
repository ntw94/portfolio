package project.forums.controller.member;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MemberForm {
    //회원 정보
    private int memberNo;
    private String memberId;
    private String memberPwd;
    private String memberPwdChk;
    private String memberName;
    private String memberPhone;
    private LocalDateTime memberRegiDate;

    //회원 프로필사진 관련
    private List<MultipartFile> imageFiles;
    private MultipartFile attachFile;
}
