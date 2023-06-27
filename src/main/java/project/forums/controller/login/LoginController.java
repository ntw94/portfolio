package project.forums.controller.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.Member;
import project.forums.service.LoginService;
import project.forums.config.session.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping()
    public String loginForm(@Valid @ModelAttribute LoginForm form,
                            BindingResult bindingResult,
                            @RequestParam(defaultValue = "/") String redirectURL,
                            Model model){
        model.addAttribute("redirectURL",redirectURL);
        return "login/loginForm";
    }

    @PostMapping()
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request
                        ){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(),form.getPassword());

        if(loginMember == null){
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다.");

            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError obj : errors) {
                log.info("error = {} ",obj.getCode());
                log.info("error = {} ",obj.getObjectName());
                log.info("error = {} ",obj.getDefaultMessage());
                
                String[] codes= obj.getCodes();
                for (String code : codes) {
                    System.out.println("code = " + code);
                }
            }
            return "login/loginForm";
        }

        //로그인 성공 처리
        HttpSession session = request.getSession();//true가 기본값
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);

        log.info("redirectURL = {} ",redirectURL);
        return "redirect:"+redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

        return "redirect:/";
    }
}
