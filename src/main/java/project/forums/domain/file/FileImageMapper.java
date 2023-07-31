package project.forums.domain.file;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface FileImageMapper {

    void getMemberImageOne(String memberId);
    void saveMemberProfileImage(FileBoardImage fileBoardImage);
    void updateMemberProfileImage(FileBoardImage fileBoardImage);

    FileBoardImage getBoardImageOne(String boardUri);
    void saveBoardImage(FileBoardImage fileBoardImage);
    void updateBoardImage(FileBoardImage fileBoardImage);
}
