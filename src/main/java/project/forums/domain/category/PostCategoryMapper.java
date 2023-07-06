package project.forums.domain.category;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PostCategoryMapper {
    List<PostCategory> getListAll(String boardUri);

    int setInsert(PostCategory postCategory);
    int setUpdate(PostCategory postCategory);
    int setDelete(PostCategory postCategory);

}
