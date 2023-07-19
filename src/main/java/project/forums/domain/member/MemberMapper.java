package project.forums.domain.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import project.forums.domain.board.Board;
import project.forums.domain.file.ImageProfile;
import project.forums.domain.member.Member;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface MemberMapper {
     List<Member> getListAll();
     List<Member> getListWithPagingSearch(Map<String,Object> map);
     Member getListOne(String memId);
     int setInsert(Member member);
     int setUpdate(Member member);
     int setDelete(Member member);

     //이미지 프로필사진 관련
     void saveProfileImage(ImageProfile imageProfile);
     ImageProfile loadProfileImage(String memberId);
     void updateProfileImage(ImageProfile imageProfile);
     List<Board> getFavorBoards(String memberId);

     //페이징 관련
     int getTotalMember(Map<String,Object> map);
}
