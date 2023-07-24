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

    //게시판 관리자 활동해제 처리부분( 다시 활성화 )
    @PostMapping("/member/stop/{boardUri}")
    public String stopMemberManage(@ModelAttribute ManageStopMemberForm form){

        log.info("{}",form);
        manageService.unlockMember(form);


        return "redirect:/manage/member/stop/{boardUri}";
    }

    //게시판 관리자 팝업창
    @GetMapping("/member/stop/popup/{boardUri}")
    public String stopMemberGetPopup(@ModelAttribute ManageStopMemberForm form){

        log.info("Get: {}",form);

        return "manage/member-stop-popup";
    }

    //게시판 관리자 팝업창Post
    @PostMapping("/member/stop/popup/{boardUri}")
    public String stopMemberPostPopup(@ModelAttribute ManageStopMemberForm form){

        log.info("Post: {}",form);

        return "manage/member-stop-popup";
    }

    //차단 회원 등록
    @PostMapping("/member/stop/popup/{boardUri}/add")
    public String stopMemberPopupAdd(@ModelAttribute ManageStopMemberForm form){

        //1. 차단해야할 아이디를 선별한다.(이미 정지된 유저는 제외)
        // select * from stop_member where board_uri = #{boardUri}
        // and member_id not in <forEach>
        // in not 하면되네

        //2. 위에 걸러진 아이디를 다시 db에 저장한다.
        //3. stopMEmberSaveForm 해서 정지날짜 추가해주고
        return "redirect:/manage/member/stop/{boardUri}";
    }

}
