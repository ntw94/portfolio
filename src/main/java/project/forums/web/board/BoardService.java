package project.forums.web.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.forums.domain.board.*;
import project.forums.domain.category.PostCategoryMapper;
import project.forums.web.board.form.BoardListForm;
import project.forums.web.board.form.BoardSaveForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    private final BoardFavorMapper boardFavorMapper;
    private final BoardRoleMapper boardAuthMapper; // 게시판
    private final PostCategoryMapper postCategoryMapper;

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
    public Integer boardCreate(BoardSaveForm boardSaveForm){

        log.info("board:{}",boardSaveForm);

        Board board = new Board();
        board.setBoardTitle(boardSaveForm.getBoardTitle());
        board.setBoardUri(boardSaveForm.getBoardUri());
        board.setBoardDescription(boardSaveForm.getBoardDescription());
        board.setMemberId(boardSaveForm.getMemberId());
        board.setMainCategoryId(boardSaveForm.getBoardMainCategoryId());
        board.setSubCategoryId(boardSaveForm.getBoardSubCategoryId());

        BoardRole boardAuth = new BoardRole();
        boardAuth.setBoardUri(boardSaveForm.getBoardUri());
        boardAuth.setMemberId(boardSaveForm.getMemberId());
        boardAuth.setBoardRole(BoardPosition.MANAGER);
        boardAuthMapper.setInsert(boardAuth);

        return boardMapper.setInsert(board);
    }

    public void createPostCategory(String boardUri){
        postCategoryMapper.createPostCategory(boardUri);
    }


    public List<BoardFavor> getFavorBoards(String memberId){
        List<BoardFavor> list = boardFavorMapper.getFavorBoards(memberId);
        return list;
    }
    public List<BoardFavor> getListAllContainFavor(String memberId){
        List<BoardFavor> list = boardFavorMapper.getListAllContainFavor(memberId);
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

    public List<Board> getBoardRankList(){

        List<Board> list = boardMapper.getBoardRankList();
        log.info("{}",list);
        return list;
    }

    public List<BoardMainCategory> getBoardMainCategoryList(){
        return boardMapper.getBoardMainCategory();
    }

    public List<BoardSubCategory> getBoardSubCategoryList(){
        return boardMapper.getBoardSubCategory();
    }

    public Integer checkBoardUri(String boardUri){
        int isDupli = boardMapper.checkBoardUri(boardUri);
        log.info("Dup: {}",isDupli);
        return isDupli;
    }

    /* 이거 ajax로 받아야하네 */
    /* 게시판 메인카테고리에 해당하는 게시판들 모두 가져오기 */
    public List<Board> findByBoardMainCategoryList(int page, int mainCategoryId, Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("perPageSize",10);
        map.put("beginPage",(page-1)*10);
        map.put("mainCategoryId",mainCategoryId);

        int totalMember = boardMapper.getCountBoardMainCategoryChild(mainCategoryId);
        PageHandler pageHandler = new PageHandler(page,10,totalMember);
        model.addAttribute("page",pageHandler);

        List<Board> list =  boardMapper.findByBoardMainCategoryList(map);
        return list;
    }
}
