package project.forums.domain.manage;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import project.forums.domain.comment.Comment;
import project.forums.domain.post.Post;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ManageMapper {

    void manageDeletePost(Map<String,Object> map); // boardUri,post.id
    void manageDeleteListPost(Map<String,Object> map);

    List<Post> manageGetDeletedPostListWithSearch(Map<String,Object> map);
    int getDeletedPostCount(Map<String,Object> map);
    int getSearchPostCount(Map<String,Object> map);
    void setRestorePosts(Map<String,Object> map);
    void setRestorePostOne(Map<String,Object> map);
    List<Comment> getDeletedCommentList(Map<String,Object> map);

}
