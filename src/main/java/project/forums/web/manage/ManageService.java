package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.forums.domain.comment.Comment;
import project.forums.domain.comment.CommentMapper;
import project.forums.domain.file.FileStore;
import project.forums.domain.member.Member;
import project.forums.domain.member.MemberMapper;
import project.forums.domain.member.StopMember;
import project.forums.domain.member.StopMemberMapper;
import project.forums.domain.post.PostMapper;
import project.forums.web.board.PageHandler;
import project.forums.web.login.LoginService;
import project.forums.web.manage.form.ManageMemberForm;
import project.forums.web.manage.form.ManageStopMemberForm;
import project.forums.web.member.MemberService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageService {
    private final MemberMapper memberMapper;
    private final StopMemberMapper stopMemberMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final FileStore fileStore;

    public List<Member> getMemberList(ManageMemberForm form,Model model){

        //pagin을 하려면 total을 구해야한다.
        Map<String, Object> map= new HashMap<>();
        map.put("keyword",form.getKeyword());
        map.put("boardUri",form.getBoardUri());
        map.put("perPageSize",form.getPerPageSize());
        map.put("beginPage",(form.getPage()-1)*form.getPerPageSize());

        int totalMember = memberMapper.getTotalMember(map);
        PageHandler pageHandler = new PageHandler(form.getPage(),form.getPerPageSize(),totalMember);
        model.addAttribute("page",pageHandler);
        model.addAttribute("keyword",form.getKeyword());

        return memberMapper.getListWithPagingSearch(map);
    }

    public List<StopMember> getStopMemberList(ManageStopMemberForm form, Model model){

        Map<String, Object> map = new HashMap<>();
        map.put("keyword",form.getKeyword());
        map.put("boardUri",form.getBoardUri());
        map.put("perPageSize",form.getPerPageSize());
        map.put("beginPage",(form.getPage()-1)*form.getPerPageSize());

        int totalMember = stopMemberMapper.getTotalStopMember(map);
        PageHandler pageHandler = new PageHandler(form.getPage(),form.getPerPageSize(),totalMember);
        model.addAttribute("page",pageHandler);
        model.addAttribute("keyword",form.getKeyword());

        return  stopMemberMapper.getListWithPagingSearch(map);
    }

    //게시판 멤버 활동해제
    public void unlockMember(ManageStopMemberForm form){

        Map<String, Object> map = new HashMap<>();
        map.put("chkMember",form.getChkMember());
        map.put("boardUri",form.getBoardUri());

        stopMemberMapper.setDelete(map);
    }

    public List<String> getPopupStopMemberList(ManageStopMemberForm form){

        Map<String,Object> map = new HashMap<>();
        map.put("boardUri",form.getBoardUri());

        List<StopMember> filterList = stopMemberMapper.getListAll(map); // 차단된 회원 아이디 조회

        //차단된 회원 제거
        List<String> stopMemberIdList = form.getChkMember().stream()
                .filter( input -> filterList.stream().map(StopMember::getMemberId)
                .noneMatch(filter -> input.equals(filter))).collect(Collectors.toList());

        return stopMemberIdList;
    }

    //하루 총 글
    public int getTodayBoardPosts(String boardUri){

        return postMapper.getTodayPosts(boardUri);
    }
    //하루 총 댓글량
    public int getTodayBoardComments(String boardUri){

        return commentMapper.getTodayComments(boardUri);
    }

}
