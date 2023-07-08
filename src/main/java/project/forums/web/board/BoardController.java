package project.forums.web.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.board.Board;
import project.forums.domain.category.PostCategory;
import project.forums.domain.member.Member;
import project.forums.domain.post.Post;
import project.forums.web.board.form.BoardSaveForm;
import project.forums.web.login.LoginService;
import project.forums.web.post.PostService;
import project.forums.web.post.form.PostListForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final PostService postService;
    private final LoginService loginService;

    //게시판 만들기
    @GetMapping("/add")
    public String boardCreateForm(HttpServletRequest request,Model model){
        sessionCheck(request,model);

        return "board/add";
    }
    @PostMapping("/add")
    public String boardCreate(@ModelAttribute BoardSaveForm boardSaveForm, BindingResult bindingResult){
        
        Board board = new Board();
        board.setBoardTitle(boardSaveForm.getBoardTitle());
        board.setBoardUri(boardSaveForm.getBoardUri());
        board.setBoardDescription(boardSaveForm.getBoardDescription());
        board.setMemberId(boardSaveForm.getMemberId());
        boardService.boardCreate(board);

        return "redirect:/";
    }
    
    @GetMapping("/{boardUri}")
    public String boardMain(HttpServletRequest request,
                            Model model,
                            @PathVariable String boardUri,
                            @RequestParam(defaultValue = "1") Integer p,
                            @RequestParam(required = false) String category
    ){
        //게시판 있는지 체크 없으면 404 페이지 출력

        log.info("page= {}",p);
        log.info("category = {}",category);

        sessionCheck(request,model);
        Board board = boardService.getBoardOne(boardUri);
        model.addAttribute("board",board);
        model.addAttribute("boardUri",boardUri);

        if(p == 1) {
            List<PostListForm> noticeList = postService.getNoticePosts(boardUri);
            model.addAttribute("nList",noticeList);
        }

        List<PostListForm> list = postService.getPosts(boardUri,category,p,10,model);
        model.addAttribute("list",list);

        List<PostCategory> clist = postService.getPostCategories(boardUri);
        model.addAttribute("cateList",clist);

        return "board/main";//
    }

    @ResponseBody
    @PostMapping("/favor/{memberId}/{boardId}/add")
    private void doBoardFavorites(@PathVariable String memberId, @PathVariable int boardId){
        boardService.addFavor(memberId,boardId);
    }

    @ResponseBody
    @GetMapping("/favor/{memberId}/{boardId}/delete")
    private void deleteBoardFavorites(@PathVariable String memberId, @PathVariable int boardId){
        boardService.deleteFavor(memberId,boardId);
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
