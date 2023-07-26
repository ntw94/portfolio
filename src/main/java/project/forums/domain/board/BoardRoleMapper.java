package project.forums.domain.board;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/* 게시판 매니저 관련 Mapper */
@Mapper
@Component
public interface BoardRoleMapper {

//    public List<BoardAuth> getList();
//    public BoardAuth getBoardAuthOne(String boardUri, String memberId);
    public int setInsert(BoardRole boardRole);
//    public int setUpdate(BoardAuth boardAuth);
//    public int setDelete(String boardUri, String memberId);
}
