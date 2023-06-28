package project.forums.web.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.board.Board;
import project.forums.domain.member.Member;
import project.forums.web.board.form.BoardSaveForm;
import project.forums.web.login.LoginService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final LoginService loginService;
    
    @GetMapping("/add")
    public String boardCreateForm(HttpServletRequest request,Model model){
        sessionCheck(request,model);

        return "board/add";
    }
    @PostMapping("/add")
    public String boardCreate(@ModelAttribute BoardSaveForm boardSaveForm, BindingResult bindingResult){
        
        //유효성검증 넣기
        Board board = new Board();
        board.setBoardTitle(boardSaveForm.getBoardTitle());
        board.setBoardUri(boardSaveForm.getBoardUri());
        board.setBoardDescription(boardSaveForm.getBoardDescription());
        board.setMemberId(boardSaveForm.getMemberId());
        boardService.boardCreate(board);
        
        //나중에 만들자마자 접속하게 만들기
        return "redirect:/";
    }
    
    @GetMapping("/{boardUri}")
    public String boardMain(HttpServletRequest request,Model model,@PathVariable String boardUri ){
        sessionCheck(request,model);
        Board board = boardService.getBoardOne(boardUri);
        model.addAttribute("board",board);

        return "board/main";//?
    }

    //세션체크
    private void sessionCheck(HttpServletRequest request, Model model) {
        Member loginMember = loginService.sessionCheck(request);
        log.info("loginMem11ber = {}",loginMember);

        if(loginMember != null){
            model.addAttribute("member",loginMember);
        }
    }
}
