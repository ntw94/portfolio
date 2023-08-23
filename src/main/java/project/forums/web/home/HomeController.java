package project.forums.web.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.forums.domain.board.Board;
import project.forums.domain.board.BoardFavor;
import project.forums.domain.board.BoardMainCategory;
import project.forums.domain.member.Member;
import project.forums.web.board.BoardService;
import project.forums.web.board.form.BoardListForm;
import project.forums.web.login.LoginService;
import project.forums.web.member.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final LoginService loginService;
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/")
    public String home(HttpServletRequest request,Model model){

        sessionCheck(request, model);

        List<Board> boardListWithCategory = boardService.findByBoardMainCategoryList(1,1,model);

        model.addAttribute("boardCategoryList",boardListWithCategory);
        model.addAttribute("board",boardService.getBoardListForm());
        model.addAttribute("rankList",boardService.getBoardRankList());
        model.addAttribute("boardMainCategoryList",boardService.getBoardMainCategoryList());

        log.info("{}",boardListWithCategory);

        return "home";
    }
    @GetMapping("/favor")
    public String favor(HttpServletRequest request, Model model){
        sessionCheck(request, model);

        Member loginMember = loginService.sessionCheck(request);

        List<BoardFavor> list = boardService.getFavorBoards(loginMember.getMemberId());
        model.addAttribute("list",list);

        log.info("favor: {}",list);

        return "favor/favor";
    }
    @GetMapping("/boards")
    public String boards(HttpServletRequest request, Model model){
        sessionCheck(request,model);

        Member loginMember = (Member)model.getAttribute("member");
        if(loginMember == null){
            model.addAttribute("board",boardService.getBoardListForm());
            return "search/board-search";
        }

        List<BoardFavor> list = boardService.getListAllContainFavor(loginMember.getMemberId());
        model.addAttribute("list",list);

        return "search/board-search-user";
    }

    private void sessionCheck(HttpServletRequest request, Model model) {
        Member loginMember = loginService.sessionCheck(request);
        log.info("loginMem11ber = {}",loginMember);

        if(loginMember != null){
            model.addAttribute("member",loginMember);
        }
    }


    @ResponseBody
    @RequestMapping("/board/mainCategories")
    public List<BoardMainCategory> getBoardMainCategories(){
        return boardService.getBoardMainCategoryList();
    }

    @ResponseBody
    @RequestMapping("/board/mainCategories/child")
    public void getChildBoardMainCategories(int page,int mainCategoryId,Model model){
        System.out.println();
        log.info("{}",boardService.findByBoardMainCategoryList(page,mainCategoryId,model));
    }

}
