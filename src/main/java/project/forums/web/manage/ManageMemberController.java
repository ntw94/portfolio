package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.member.Member;
import project.forums.domain.member.StopMember;
import project.forums.web.comment.CommentService;
import project.forums.web.manage.form.ManageMemberForm;
import project.forums.web.manage.form.ManageSaveStopMemberForm;
import project.forums.web.manage.form.ManageStopMemberForm;
import project.forums.web.post.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manage")
@Controller
public class ManageMemberController {

    private final PostService postService;
    private final CommentService commentService;
    private final ManageService manageService;

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

    // 활동해제 버튼 눌렀을때
    @PostMapping("/member/stop/{boardUri}")
    public String stopMemberManage(@ModelAttribute ManageStopMemberForm form){

        log.info("{}",form);
        manageService.unlockMember(form);


        return "redirect:/manage/member/stop/{boardUri}";
    }
    //게시판 관리자 팝업창Post
    @PostMapping("/member/stop/popup/{boardUri}")
    public String stopMemberPostPopup(@ModelAttribute ManageStopMemberForm form,@PathVariable String boardUri,Model model){

        List<String> list = manageService.getPopupStopMemberList(form);

        log.info("{}",list);

        model.addAttribute("list",list);
        model.addAttribute("boardUri",boardUri);

        return "manage/member-stop-popup";
    }

    //차단 회원 등록
    @PostMapping("/member/stop/popup/{boardUri}/add")
    public void stopMemberPopupAdd(@ModelAttribute ManageSaveStopMemberForm form, HttpServletResponse response){

        log.info("popupSave: {}",form);

        manageService.saveStopMember(form);

        log.info("{}",form.getReason());
        try {
            PrintWriter out = response.getWriter();

            out.println("<script>" +
                    "opener.parent.location.reload();"+
                    "window.close()" +
                    " </script>");

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
