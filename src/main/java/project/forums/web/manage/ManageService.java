package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.forums.domain.comment.Comment;
import project.forums.domain.comment.CommentMapper;
import project.forums.domain.file.FileStore;
import project.forums.domain.member.Member;
import project.forums.domain.member.MemberMapper;
import project.forums.domain.post.PostMapper;
import project.forums.web.login.LoginService;
import project.forums.web.member.MemberService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageService {
    private final MemberMapper memberMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final FileStore fileStore;

    public List<Member> getMemberList(){

        return memberMapper.getListAll();
    }

    public int getTodayBoardPosts(String boardUri){

        return postMapper.getTodayPosts(boardUri);
    }
    public int getTodayBoardComments(String boardUri){

        return commentMapper.getTodayComments(boardUri);
    }

}
