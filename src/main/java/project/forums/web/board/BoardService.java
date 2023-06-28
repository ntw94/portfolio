package project.forums.web.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.forums.domain.board.Board;
import project.forums.domain.board.BoardMapper;
import project.forums.web.board.form.BoardListForm;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

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
        return boardMapper.setInsert(board);
    }

}
