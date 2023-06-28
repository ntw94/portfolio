package project.forums.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.forums.domain.member.Member;
import project.forums.domain.login.LoginMapper;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class LoginServiceTest {
    private final LoginMapper loginMapper;
    @Autowired
    LoginServiceTest(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    @Test
    public void loginTest() throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("memberId","asdf");
        map.put("memberPwd","asdf");

        Member member = loginMapper.signIn(map);

        System.out.println(member);
    }
}