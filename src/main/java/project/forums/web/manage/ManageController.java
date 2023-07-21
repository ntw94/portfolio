package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.comment.Comment;
import project.forums.domain.member.Member;
import project.forums.domain.member.StopMember;
import project.forums.web.comment.CommentService;
import project.forums.web.manage.form.ManageMemberForm;
import project.forums.web.manage.form.ManageStopMemberForm;
import project.forums.web.post.PostService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manage")
@Controller
public class ManageController {

    private final PostService postService;
    private final CommentService commentService;
    private final ManageService manageService;

    @GetMapping("/boards/{boardUri}")
    public String boardManageHome(@PathVariable String boardUri, Model model){


        int todayPosts = manageService.getTodayBoardPosts(boardUri);
        int todayComments = manageService.getTodayBoardComments(boardUri);

        model.addAttribute("todayPosts",todayPosts);
        model.addAttribute("todayComments",todayComments);

        return "manage/home";
    }


    //회원관리
    @GetMapping("/member/{boardUri}")
    public String boardMemberManage(@PathVariable String boardUri
                                    , @ModelAttribute ManageMemberForm manageMemberForm
                                    , Model model){

        manageMemberForm.setBoardUri(boardUri);
        List<Member> list = manageService.getMemberList(manageMemberForm,model);
        model.addAttribute("mList", list);



        return "manage/member";
    }

    @GetMapping("/member/stop/{boardUri}")
    public String stopMemberManage(@ModelAttribute ManageStopMemberForm stopForm, @PathVariable String boardUri, Model model){

        log.info("{}",stopForm);

        List<StopMember> list = manageService.getStopMemberList(stopForm,model);
        model.addAttribute("stopList",list);


        return "manage/member-stop";
    }

    @PostMapping("/member/stop/{boardUri}")
    public String stopMemberManage(@ModelAttribute ManageStopMemberForm form){

        // 버튼 입력에 따른 차단을 해제하고 풀어주고 하면됨



        return "redirect:/manage/member/stop/{boardUri}";
    }
}
