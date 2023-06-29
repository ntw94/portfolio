package project.forums.web.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.forums.domain.member.Member;
import project.forums.web.member.form.MemberListForm;
import project.forums.web.member.form.MemberSaveForm;
import project.forums.web.member.form.MemberUpdateForm;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memService;

    @GetMapping // 멤버 조회
    public String memList(Model model){
        List<MemberListForm> list = memService.getMemberFormList();

        model.addAttribute("list",list);
        return "member/list";
    }

    @GetMapping("/add") // 회원가입 폼
    public String memJoinForm(){
        return "member/add";
    }

    @PostMapping("/add") // 회원추가
    public String memJoin(@ModelAttribute MemberSaveForm memberSaveForm) throws IOException {

        Member member = new Member();
        member.setMemberId(memberSaveForm.getMemberId());
        member.setMemberName(memberSaveForm.getMemberName());
        member.setMemberPhone(memberSaveForm.getMemberPhone());
        member.setMemberPwd(memberSaveForm.getMemberPwd());
        Integer res = memService.memberJoin(member);

        //기본 프로필이미지 셋팅
        memService.initProfileImgSave(memberSaveForm);
        log.info("res = {} ",res);

        return "redirect:/members";
    }

    @GetMapping("/{memberId}") // 회원 조회
    public String memFindById(@PathVariable("memberId") String memberId,Model model){
        Member member = memService.getMemberOne(memberId);
        model.addAttribute("member",member);

        return "member/view";
    }

    @GetMapping("/{memberId}/edit") // 회원 수정
    public String memEditForm(@PathVariable("memberId") String memberId,Model model) throws MalformedURLException {
        Member member = memService.getMemberOne(memberId);
        model.addAttribute("member",member);

        String profileImg = memService.profileImgFullPath(member.getMemberId());
        model.addAttribute("profileImg",profileImg);

        return "member/edit";
    }
    @PostMapping("/{memberId}/edit")
    public String memEdit(@ModelAttribute MemberUpdateForm memberUpdateForm, @PathVariable("memberId") String memberId) throws IOException {

        Member member = memService.getMemberOne(memberId);
        member.setMemberPhone(memberUpdateForm.getMemberPhone());
        member.setMemberName(memberUpdateForm.getMemberName());
        memService.memberUpdate(member);
        memService.profileImgUpdate(memberUpdateForm);

        return "redirect:/members/{memberId}";
    }

    // 회원 삭제
    @GetMapping("/{memberId}/delete")
    public String memDelete(@PathVariable("memberId") String memberId){
        Member member = memService.getMemberOne(memberId);
        int res = memService.memberDelete(member);

        return "redirect:/members";
    }

    //이미지 불러오기
    @ResponseBody
    @GetMapping("/images/{memberId}")
    public Resource downloadImage(@PathVariable String memberId) throws MalformedURLException {
        Member member = memService.getMemberOne(memberId);
        String imgFilePath = memService.profileImgFullPath(member.getMemberId());

        return new UrlResource("file:"+imgFilePath);
    }

}
