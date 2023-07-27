package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.board.BoardPosition;
import project.forums.domain.board.BoardRole;
import project.forums.domain.member.Member;
import project.forums.domain.member.StopMember;
import project.forums.web.comment.CommentService;
import project.forums.web.comment.form.CommentReplyForm;
import project.forums.web.manage.form.*;
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

    //회원관리 홈
    @GetMapping("/member/{boardUri}")
    public String boardMemberManage(@PathVariable String boardUri
            , @ModelAttribute ManageMemberForm manageMemberForm
            , Model model){

        manageMemberForm.setBoardUri(boardUri);
        List<Member> list = manageService.getMemberList(manageMemberForm,model);
        model.addAttribute("mList", list);

        return "manage/member/member";
    }

    //차단 회원 페이지 홈
    @GetMapping("/member/stop/{boardUri}")
    public String stopMemberManage(@ModelAttribute ManageStopMemberForm stopForm, @PathVariable String boardUri, Model model){

        log.info("{}",stopForm);

        List<StopMember> list = manageService.getStopMemberList(stopForm,model);
        model.addAttribute("stopList",list);

        return "manage/member/member-stop";
    }

    // 활동해제 버튼 눌렀을때
    @PostMapping("/member/stop/{boardUri}")
    public String stopMemberManage(@ModelAttribute ManageStopMemberForm form){

        log.info("{}",form);
        manageService.unlockMember(form);


        return "redirect:/manage/member/stop/{boardUri}";
    }
    //게시판 관리자 팝업창 띄우기
    @PostMapping("/member/stop/popup/{boardUri}")
    public String stopMemberPostPopup(@ModelAttribute ManageStopMemberForm form,@PathVariable String boardUri,Model model){

        List<String> list = manageService.getPopupStopMemberList(form);

        log.info("{}",list);

        model.addAttribute("list",list);
        model.addAttribute("boardUri",boardUri);

        return "manage/member/member-stop-popup";
    }

    //차단 회원 등록 처리
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

    /* 스탭관리 홈*/
    @GetMapping("/member/step/{boardUri}")
    public String stepMemberHome(@PathVariable String boardUri,Model model){

        List<BoardRole> stepList = manageService.getStepList(boardUri);
        model.addAttribute("stepList",stepList);

        return "manage/member/step";
    }

    /* ajax 아이디 검색 받는 부분 */
    @ResponseBody
    @PostMapping("/member/step/{boardUri}/search")
    public String stepMemberSearch(
            @RequestBody ManageSearchStepMemberForm form){

        log.info(" = {}", form);
        String result = manageService.getSearchMemberId(form);

        if(result == null){
            return "";
        }
        return result;
    }

    /* 게시판 매니저 등록 */
    @PostMapping("/member/step/{boardUri}/add")
    public String stepMemberAdd(
            @ModelAttribute ManageSaveStepMemberForm form
            ){

        log.info("{}",form);

        int managerCount = 0;
        if(form.getRole()== BoardPosition.SUB_MANAGER){
            managerCount = manageService.getTotalSubManager(form.getBoardUri());
        }
        if(managerCount == 0){
            manageService.saveStepMember(form);
        }else {
           //에러 메세지 보내준다.
            log.info("이미 부매니저는 1명만 가능합니다.!!");
        }

        return "redirect:/manage/member/step/{boardUri}";
    }

    @PostMapping("/member/step/{boardUri}/delete")
    public String stepMemberDelete(@ModelAttribute ManageDeleteStepMemberForm form){

        log.info("{}",form);
        manageService.deleteStepMember(form);

        return "redirect:/manage/member/step/{boardUri}";
    }

}
