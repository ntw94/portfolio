package project.forums.domain.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface StopMemberMapper {

    List<StopMember> getListWithPagingSearch(Map<String,Object> map);
    List<StopMember> getListAll(Map<String,Object> map);
    StopMember getListOne(StopMember stopMember);
    void setInsert(Map<String,Object> map);
    void setDelete(Map<String,Object> map);
    void setUpdate(StopMember stopMember);

    //차단한 회원 구하기
    int getTotalStopMember(Map<String,Object> map);

}
