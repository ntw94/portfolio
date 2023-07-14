package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.forums.domain.comment.Comment;
import project.forums.web.comment.CommentService;
import project.forums.web.member.MemberService;
import project.forums.web.post.PostService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manage")
@Controller
public class ManageController {

    private final PostService postService;
    private final MemberService memberService;
    private final CommentService commentService;


    @GetMapping("/boards/{boardUri}")
    public String boardManageHome(@PathVariable String boardUri, Model model){

        //세션체크해야됨

        int todayPosts = postService.getTodayPosts(boardUri);
        int todayComments = commentService.getTodayComments(boardUri);

        model.addAttribute("todayPosts",todayPosts);
        model.addAttribute("todayComments",todayComments);

        return "manage/home";
    }

    @GetMapping("/member/{boardUri}")
    public String boardMemberManage(@PathVariable String boardUri, Model model){


        return "mange/member";
    }

}
