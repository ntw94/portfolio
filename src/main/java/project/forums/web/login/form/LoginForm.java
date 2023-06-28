package project.forums.web.login.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty(message = "아이디를 입력하세요")
    private String loginId;
    @NotEmpty
    private String password;
}
