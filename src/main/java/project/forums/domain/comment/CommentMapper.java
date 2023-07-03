package project.forums.domain.comment;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import project.forums.domain.board.Board;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {
    List<Comment> getListAll(Integer postId);
    Comment getListOne(int id); //?
    int setInsert(Comment comment);
    int setUpdate(Comment comment);
    int setDelete(Comment comment);


}
