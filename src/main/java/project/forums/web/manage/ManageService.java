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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //하루 총 글
    public int getTodayBoardPosts(String boardUri){

        return postMapper.getTodayPosts(boardUri);
    }
    //하루 총 댓글량
    public int getTodayBoardComments(String boardUri){

        return commentMapper.getTodayComments(boardUri);
    }

}
