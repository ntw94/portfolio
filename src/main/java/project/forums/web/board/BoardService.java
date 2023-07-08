package project.forums.web.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.forums.domain.board.*;
import project.forums.web.board.form.BoardListForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    private final BoardManagerMapper managerMapper;
    private final BoardFavorMapper boardFavorMapper;

    public List<Board> getBoardList(){
        return boardMapper.getListAll();
    }
    public List<BoardListForm> getBoardListForm(){
        List<Board> list = getBoardList();
        List<BoardListForm> resList = new ArrayList<>();

        for (Board board : list) {
            BoardListForm form = new BoardListForm();
            form.setBoardTitle(board.getBoardTitle());
            form.setBoardDescription(board.getBoardDescription());
            form.setBoardUri(board.getBoardUri());
            form.setUploadFileName(board.getUploadFileName());
            form.setStoreFileName(board.getBoardTitle());
            form.setBoardCreateDate(board.getBoardCreateDate());
            form.setMemberId(board.getMemberId());

            resList.add(form);
        }

        return resList;
    }
    public Board getBoardOne(String uri){
        return boardMapper.getListOne(uri);
    }
    public Integer boardCreate(Board board){


        BoardManager boardManager = new BoardManager();
        boardManager.setId(board.getId());
        boardManager.setBoardRole(BoardRole.MANAGER);
        boardManager.setBoardUri(board.getBoardUri());
        boardManager.setMemberId(board.getMemberId());

        managerMapper.setInsert(boardManager);
        return boardMapper.setInsert(board);
    }


    public List<BoardFavor> getFavorBoards(String memberId){
        List<BoardFavor> list = boardFavorMapper.getFavorBoards(memberId);
        return list;
    }

    public void addFavor(String memberId, Integer boardId){
        Map<String,Object> map = new HashMap<>();
        map.put("memberId",memberId);
        map.put("boardId",boardId);
        boardFavorMapper.setInsert(map);
    }

    public void deleteFavor(String memberId, Integer boardId){
        Map<String,Object> map = new HashMap<>();
        map.put("memberId",memberId);
        map.put("boardId",boardId);
        boardFavorMapper.setDelete(map);
    }
}
