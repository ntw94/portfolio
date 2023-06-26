package project.forums.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import project.forums.domain.file.ImageProfile;
import project.forums.domain.Member;

import java.util.List;

@Mapper
@Component
public interface MemberMapper {

     List<Member> getListAll();
     Member getListOne(String memId);
     int setInsert(Member member);
     int setUpdate(Member member);
     int setDelete(Member member);

     //이미지 프로필사진 관련
     void saveProfileImage(ImageProfile imageProfile);
     ImageProfile loadProfileImage(String memberId);
     void updateProfileImage(ImageProfile imageProfile);

}
