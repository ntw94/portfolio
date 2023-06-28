package project.forums.web.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.forums.domain.member.Member;
import project.forums.web.board.BoardService;
import project.forums.web.board.form.BoardListForm;
import project.forums.web.login.LoginService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final LoginService loginService;
    private final BoardService boardService;

    @GetMapping("/")
    public String home(HttpServletRequest request,Model model){

        sessionCheck(request, model);
        model.addAttribute("board",boardService.getBoardListForm());

        return "home";
    }

    private void sessionCheck(HttpServletRequest request, Model model) {
        Member loginMember = loginService.sessionCheck(request);
        log.info("loginMem11ber = {}",loginMember);

        if(loginMember != null){
            model.addAttribute("member",loginMember);
        }
    }
}
