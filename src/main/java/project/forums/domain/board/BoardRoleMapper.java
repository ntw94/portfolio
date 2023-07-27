package project.forums.domain.board;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


/* 게시판 매니저 관련 Mapper */
@Mapper
@Component
public interface BoardRoleMapper {

     List<BoardRole> getListAll(String boardUri);
     BoardRole getListOne(String boardUri, String memberId);
     void setInsert(BoardRole boardRole);
     void setDelete(BoardRole boardRole);
    // 부매니저 수 반환
    int getSubManagerCount(String boardUri);
//    public int setUpdate(BoardAuth boardAuth);
//    public int setDelete(String boardUri, String memberId);
}
