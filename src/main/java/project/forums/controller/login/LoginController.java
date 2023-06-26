package project.forums.controller.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller

public class LoginController {

    @GetMapping("/login")
    public String LoginForm(){
        return "login/login";
    }
}
