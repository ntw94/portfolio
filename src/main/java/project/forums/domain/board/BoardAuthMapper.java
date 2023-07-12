package project.forums.domain.board;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BoardAuthMapper {

//    public List<BoardAuth> getList();
//    public BoardAuth getBoardAuthOne(String boardUri, String memberId);
    public int setInsert(BoardAuth boardAuth);
//    public int setUpdate(BoardAuth boardAuth);
//    public int setDelete(String boardUri, String memberId);
}
