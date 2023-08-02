package project.forums.domain.category;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface PostCategoryMapper {
    List<PostCategory> getListAll(String boardUri);

    void createPostCategory(String boardUri);
    int setInsert(Map<String,Object> map);
    int setNewInsert(Map<String,Object> map);

    void setDeleteCategoryList(Map<String,Object> map);
    void setUpdateCategoryList(Map<String,Object> map);
}
