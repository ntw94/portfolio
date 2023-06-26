package project.forums.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import project.forums.controller.member.MemberForm;
import project.forums.domain.file.FileStore;
import project.forums.domain.file.ImageProfile;
import project.forums.domain.Member;
import project.forums.domain.file.UploadFile;
import project.forums.mapper.MemberMapper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final FileStore fileStore;

    public List<Member> getMemberList(){
        return memberMapper.getListAll();
    }
    public List<MemberForm> getMemberFormList() {

        List<Member> list = memberMapper.getListAll();
        List<MemberForm> formList = new ArrayList<>();

        MemberForm memberForm;
        for (Member member : list) {
            memberForm = new MemberForm();
            memberForm.setMemberNo(member.getMemberNo());
            memberForm.setMemberId(member.getMemberId());
            memberForm.setMemberName(member.getMemberName());
            memberForm.setMemberPhone(member.getMemberPhone());
            memberForm.setMemberRegiDate(member.getMemberRegiDate());

            formList.add(memberForm);
        }

        return formList;
    }
    public Member getMemberOne(String memId){
        return memberMapper.getListOne(memId);
    }
    public Integer memberJoin(Member member) {
        return memberMapper.setInsert(member);
    }
    public Integer memberUpdate(Member member){
        return memberMapper.setUpdate(member);
    }
    public Integer memberDelete(Member member){
        return memberMapper.setDelete(member);
    }

    public void initProfileImgSave(MemberForm form) throws IOException {

        ImageProfile imageProfile = new ImageProfile();
        imageProfile.setMemberId(form.getMemberId());
        imageProfile.setUploadFileName(null);
        imageProfile.setStoreFileName(null);

        memberMapper.saveProfileImage(imageProfile);
    }
    public void profileImgUpdate(MemberForm form) throws IOException{

        UploadFile attachFile = fileStore.storeFile(form.getAttachFile());

        if(attachFile == null)
            return;

        ImageProfile imageProfile = memberMapper.loadProfileImage(form.getMemberId());

        String oldImageFileName = imageProfile.getStoreFileName();

        imageProfile.setUploadFileName(attachFile.getUploadFileName());
        imageProfile.setStoreFileName(attachFile.getStoreFileName());

        memberMapper.updateProfileImage(imageProfile);// 업데이트 끝


        String oldFilePath = fileStore.getFullPath(oldImageFileName);

        File file = new File(oldFilePath);
        if(file.exists()){
            file.delete();
        }

    }

    public String profileImgFullPath(String memberId) throws MalformedURLException {

        ImageProfile imageProfile = memberMapper.loadProfileImage(memberId);
        if(imageProfile.getUploadFileName() == null && imageProfile.getStoreFileName() == null)
            return "src/main/resources/static/img/default_img.jpg";

        return fileStore.getFullPath(imageProfile.getStoreFileName());
    }
}
