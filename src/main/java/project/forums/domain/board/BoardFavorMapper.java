package project.forums.domain.board;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BoardFavorMapper {
    List<BoardManager> getListAll();
    BoardManager getListOne(String boardUri);
    int setUpdate(BoardManager board);
    int setDelete(BoardManager board);
    int setInsert(BoardManager boardManager);
}
