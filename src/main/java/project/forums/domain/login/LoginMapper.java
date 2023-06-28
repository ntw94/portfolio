package project.forums.domain.login;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import project.forums.domain.member.Member;

import java.util.Map;

@Mapper
@Component
public interface LoginMapper {
    Member signIn(Map<String,Object> map);
}
