package project.forums.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import project.forums.domain.Member;

import java.util.HashMap;
import java.util.Map;

@Mapper
@Component
public interface LoginMapper {
    Member signIn(Map<String,Object> map);
}
