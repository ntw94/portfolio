package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.forums.domain.comment.Comment;
import project.forums.domain.member.Member;
import project.forums.web.comment.CommentService;
import project.forums.web.login.LoginService;
import project.forums.web.manage.form.ManageMemberForm;
import project.forums.web.member.MemberService;
import project.forums.web.post.PostService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manage")
@Controller
public class ManageController {

    private final PostService postService;
    private final CommentService commentService;
    private final ManageService manageService;
    private final LoginService loginService;

    @GetMapping("/boards/{boardUri}")
    public String boardManageHome(@PathVariable String boardUri, Model model){


        int todayPosts = postService.getTodayPosts(boardUri);
        int todayComments = commentService.getTodayComments(boardUri);

        model.addAttribute("todayPosts",todayPosts);
        model.addAttribute("todayComments",todayComments);

        return "manage/home";
    }

    @GetMapping("/member/{boardUri}")
    public String memberManage(HttpServletRequest request, @PathVariable String boardUri, Model model){

        sessionCheck(request,model);

        List<Member> list = manageService.getMemberList();

        model.addAttribute("mList",list);

        return "manage/member";
    }
    @GetMapping("/manage/member/{boardUri}/search")
    public String memberManageSearch(@ModelAttribute ManageMemberForm form, @PathVariable String boardUri, Model model){



        return "manage/member";
    }

    @GetMapping("/step/{boardUri}")
    public String stepManage(@PathVariable String boardUri, Model model){

        return "manage/step";
    }
    @GetMapping("/post/{boardUri}")
    public String postManage(@PathVariable String boardUri, Model model){

        return "manage/post";
    }
    @GetMapping("/category/{boardUri}")
    public String postCategory(@PathVariable String boardUri, Model model){

        return "manage/category";
    }
    @GetMapping("/board/{boardUri}/edit")
    public String boardManageEdit(@PathVariable String boardUri, Model model){

        return "manage/board";
    }


    private int sessionCheck(HttpServletRequest request, Model model) {
        Member loginMember = loginService.sessionCheck(request);
        log.info("loginMember = {}",loginMember);

        if(loginMember != null){
            model.addAttribute("member",loginMember);
            return 1;
        }
        return 0;
    }
}
