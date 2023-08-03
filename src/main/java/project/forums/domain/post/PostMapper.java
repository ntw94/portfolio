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
    List<Post> getListWithSearch(Map<String,Object> map);
    Post getListOne(Post post);
    int setInsert(Post post);
    int setDelete(Post post);
    int setUpdate(Post post);

    //조회수 검색 등등 추가
    int getTotalCategoryPosts(@Param("boardUri") String boardUri,
                      @Param("category") String category);
    int getTotalBoardPosts(Map<String,Object> map);
    //조회수 올리기
    void viewsUp(Post post);
    //공지글 가져오기
    List<Post> getNoticePosts(String boardUri);

    //오늘 쓴 글의 수 가져오기
    int getTodayPosts(String boardUri);
}
