package project.forums.domain.board;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface BoardMapper {
    List<Board> getListAll();
    Board getListOne(String boardUri);
    Board getListUpdateOne(String boardUri);

    int setInsert(Board board);
    int setUpdate(Board board);
    int setDelete(Board board);

    //이미지 프로필사진 관련
    void saveBoardIconImage(Board board);
    Board loadBoardIconImage(Board board);
    void updateProfileImage(Board board);

    List<Board> getBoardRankList();

    List<BoardMainCategory> getBoardMainCategory();
    List<BoardSubCategory> getBoardSubCategory();
    Integer checkBoardUri(String boardUri);

    List<Board> findByBoardMainCategoryList(Map<String,Object> map);
    int getCountBoardMainCategoryChild(int mainCategoryId);


}
