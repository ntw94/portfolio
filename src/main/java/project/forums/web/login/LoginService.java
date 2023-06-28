package project.forums.web.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.forums.domain.member.Member;
import project.forums.domain.login.LoginMapper;
import project.forums.web.session.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginMapper loginMapper;

    public Member login(String memberId, String memberPwd){
        Map<String,Object> map = new HashMap<>();
        map.put("memberId",memberId);
        map.put("memberPwd",memberPwd);
        Member member = loginMapper.signIn(map);
        return member;
    }

    public Member sessionCheck(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null)
            return null;

        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        log.info("loginMember = {} , {}",loginMember,session);

        if(loginMember == null){
            return null;
        }

        return loginMember;
    }

}
