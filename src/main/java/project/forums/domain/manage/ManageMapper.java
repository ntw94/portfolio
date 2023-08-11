package project.forums.domain.manage;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Mapper
@Component
public interface ManageMapper {

    void manageDeletePost(Map<String,Object> map); // boardUri,post.id
    void manageDeleteListPost(Map<String,Object> map);
}
