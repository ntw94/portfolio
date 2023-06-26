package project.forums.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import project.forums.domain.Member;
import project.forums.mapper.LoginMapper;
import project.forums.mapper.MemberMapper;

import java.util.HashMap;
import java.util.Map;

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

}
