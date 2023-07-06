package project.forums.domain.post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface PostMapper {
    List<Post> getListAll(Map<String,Object> map);
    Post getListOne(Post post);
    int setInsert(Post post);
    int setDelete(Post post);
    int setUpdate(Post post);
    //조회수 검색 등등 추가
    int getTotalPosts(@Param("boardUri") String boardUri,
                      @Param("category") String category);

    //조회수 올리기
    void viewsUp(Post post);
}
