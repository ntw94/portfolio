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

    /* 게시글 하나 삭제 */
    void manageDeletePost(Map<String,Object> map); // boardUri,post.id
    /* 게시글 여러 삭제 */
    void manageDeleteListPost(Map<String,Object> map);

    /* 삭제된 게시글 불러오기(검색어) */
    List<Post> manageGetDeletedPostListWithSearch(Map<String,Object> map);

    /* 삭제된 게시글 수 */
    int getDeletedPostCount(Map<String,Object> map);
    /* 검색된 게시글 수 */
    int getSearchPostCount(Map<String,Object> map);

    /* 삭제된 게시글 여럿 복구 */
    void setRestorePosts(Map<String,Object> map);
    /* 삭제된 게시글 하나 복구 */
    void setRestorePostOne(Map<String,Object> map);

    /* 삭제된 댓글 전체 조회 */
    List<Comment> getDeletedCommentList(Map<String,Object> map);
    /* 삭제된 댓글 수 조회 */
    int getDeletedCommentCount(Map<String,Object> map);

    void setRestoreCommentOne(Map<String,Object> map);
    void setRestoreComments(Map<String,Object> map);

}
