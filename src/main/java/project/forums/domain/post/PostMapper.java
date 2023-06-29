package project.forums.domain.post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PostMapper {
    List<Post> getListAll(String boardUri);
    Post getListOne(Post post);
    int setInsert(Post post);
    int setDelete(Post post);
    int setUpdate(Post post);

    //조회수 검색 등등 추가
}
