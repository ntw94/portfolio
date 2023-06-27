package project.forums.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Mock
    private MemberService service;

    @Test
    public void getList() throws Exception{
        System.out.println(service.getMemberList());
    }
}