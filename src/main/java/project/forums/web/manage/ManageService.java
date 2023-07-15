package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.forums.domain.member.Member;
import project.forums.web.login.LoginService;
import project.forums.web.member.MemberService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageService {
    private final MemberService memberService;

    public List<Member> getMemberList(){
        return memberService.getMemberList();

    }

}
