package project.forums.controller.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.forums.domain.Member;
import project.forums.service.LoginService;
import project.forums.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final LoginService loginService;

    @GetMapping("/")
    public String home(HttpServletRequest request,Model model){

        Member loginMember = loginService.sessionCheck(request);
        log.info("loginMem11ber = {}",loginMember);

        if(loginMember != null){
            model.addAttribute("member",loginMember);
        }
        return "home";
    }
}
