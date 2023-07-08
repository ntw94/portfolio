package project.forums.domain.board;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface BoardFavorMapper {
    List<BoardFavor> getFavorBoards(String memberId);
    List<BoardFavor> getListAllContainFavor(String memberId);
    void setInsert(Map<String,Object> map);
    void setDelete(Map<String,Object> map);
}
