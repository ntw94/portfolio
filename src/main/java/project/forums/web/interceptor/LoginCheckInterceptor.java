package project.forums.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import project.forums.web.session.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉터 실행 = {} ",requestURI);
        HttpSession session = request.getSession(false);
        request.setAttribute("redirectURL",requestURI);

//        final Map<String, String> pathVariables = (Map<String, String>) request
//                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//
//        if(pathVariables.get("boardUri") != null && pathVariables.get("postId")!= null){
//
//            log.info("pathVariables = {}",pathVariables);
//            return true;
//        }


        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
            log.info("미인증 사용자 요청");
            response.sendRedirect("/login?redirectURL="+requestURI);
            return false;
        }

        return true;
    }

}
