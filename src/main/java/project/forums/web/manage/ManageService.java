package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.forums.domain.board.Board;
import project.forums.domain.board.BoardMapper;
import project.forums.domain.board.BoardRole;
import project.forums.domain.board.BoardRoleMapper;
import project.forums.domain.category.PostCategory;
import project.forums.domain.category.PostCategoryMapper;
import project.forums.domain.comment.Comment;
import project.forums.domain.comment.CommentMapper;
import project.forums.domain.file.FileBoardImage;
import project.forums.domain.file.FileImageMapper;
import project.forums.domain.file.FileStore;
import project.forums.domain.file.UploadFile;
import project.forums.domain.manage.ManageMapper;
import project.forums.domain.member.Member;
import project.forums.domain.member.MemberMapper;
import project.forums.domain.member.StopMember;
import project.forums.domain.member.StopMemberMapper;
import project.forums.domain.post.Post;
import project.forums.domain.post.PostMapper;
import project.forums.web.board.PageHandler;
import project.forums.web.login.LoginService;
import project.forums.web.manage.form.*;
import project.forums.web.member.MemberService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManageService {
    private final MemberMapper memberMapper;
    private final StopMemberMapper stopMemberMapper;
    private final BoardMapper boardMapper;
    private final BoardRoleMapper boardRoleMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final FileStore fileStore;
    private final PostCategoryMapper categoryMapper;
    private final FileImageMapper fileImageMapper;
    private final ManageMapper manageMapper;

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

    //차단회원 등록
    public void saveStopMember(ManageSaveStopMemberForm form){
        Map<String,Object> map = new HashMap<>();
        map.put("chkMember",form.getChkMember());
        map.put("period",form.getPeriod());
        map.put("boardUri",form.getBoardUri());
        map.put("reason",form.getReason());

        stopMemberMapper.setInsert(map);

    }
    /*매니저 전체 조회 */
    public List<BoardRole> getStepList(String boardUri){

        return boardRoleMapper.getListAll(boardUri);
    }

    /* 아이디 찾기 */
    public String getSearchMemberId(ManageSearchStepMemberForm form){

        Member resultMember = memberMapper.getListOne(form.getMemberId());
        if(resultMember == null){
            return "";
        }else{
            return resultMember.getMemberId();
        }
    }

    /* 매니저 등록 추가 */

    public void saveStepMember(ManageSaveStepMemberForm form){
        if(form.getRole()== null || form.getRole().equals("")){
            return;
        }

        BoardRole boardRole = new BoardRole();
        boardRole.setMemberId(form.getMemberId());
        boardRole.setBoardUri(form.getBoardUri());
        boardRole.setBoardRole(form.getRole());

        boardRoleMapper.setInsert(boardRole);
    }

    public void deleteStepMember(ManageDeleteStepMemberForm form){

        BoardRole boardRole = new BoardRole();
        boardRole.setMemberId(form.getMemberId());
        boardRole.setBoardUri(form.getBoardUri());

        boardRoleMapper.setDelete(boardRole);
    }

    public void categoryAdd(ManageCategorySaveForm form){

        List<PostCategory> newList = new ArrayList<>();
        //새로운 추가된 카테고리 먼저 db에 추가한다.

        if(form.getCateId() == null ||
                form.getCategoryName() == null ||
                form.getCategoryName().equals("")) {

            return;
        }

        int size = form.getCategoryName().length;

        for(int i = 0; i< size;i++){
            PostCategory postCategory = new PostCategory();
            postCategory.setId(form.getCateId()[i]);
            postCategory.setCategoryMenu(form.getCategoryName()[i]);
            postCategory.setCategoryOrder(Integer.parseInt(form.getCategoryOrder()[i]));
            postCategory.setBoardUri(form.getBoardUri());
            newList.add(postCategory);
        }
        log.info("기존카테고리 변경 : {}",newList);
        Map<String,Object> map = new HashMap<>();
        map.put("newList",newList);

        categoryMapper.setUpdateCategoryList(map);
    }


    public void newCategoryAdd(ManageCategorySaveForm form){

        List<PostCategory> newList = new ArrayList<>();
        //새로운 추가된 카테고리 먼저 db에 추가한다.

        if(form.getNewCategoryName() == null || form.getNewCategoryName().equals("")) {
            //에러
            return;
        }
        if(form.getNewCategoryOrder() == null) return ;

        int size = form.getNewCategoryName().length;

        for(int i = 0; i< size;i++){
            PostCategory postCategory = new PostCategory();
            postCategory.setCategoryMenu(form.getNewCategoryName()[i]);
            postCategory.setCategoryOrder(Integer.parseInt(form.getNewCategoryOrder()[i]));
            postCategory.setBoardUri(form.getBoardUri());
            newList.add(postCategory);
        }

        log.info("새로운 카테고리 추가 : {}",newList);
        Map<String,Object> map = new HashMap<>();
        map.put("newList",newList);

        categoryMapper.setNewInsert(map);

    }


    //현재 게시판의 글 카테고리 전체 조회
    public List<PostCategory> getPostCategoryListAll(String boardUri){

        return categoryMapper.getListAll(boardUri);
    }

    //게시판 수정 데이터 조회
    public Board getBoardInfo(String boardUri){
            Board board = boardMapper.getListUpdateOne(boardUri);
            if(board == null) {
                board = boardMapper.getListOne(boardUri);
            }
        return board;
    }

    public void setUpdateBoardInfo(ManageUpdateBoardInfoForm form) throws IOException {
        Board board= new Board();
        board.setBoardUri(form.getBoardUri());
        board.setBoardDescription(form.getBoardDescription());
        board.setBoardTitle(form.getBoardTitle());
        board.setMemberId(form.getMemberId());
        board.setId(Integer.parseInt(form.getId()));

        //파일 이미지 업데이트 null이라면? 그냥 그대로 지나가면 됨
        UploadFile file = fileStore.storeFile(form.getBoardImageFile());//
        if(file != null){
            FileBoardImage fileBoardImage = new FileBoardImage();
            fileBoardImage.setUploadFileName(file.getUploadFileName());
            fileBoardImage.setStoreFileName(file.getStoreFileName());
            fileBoardImage.setBoardUri(form.getBoardUri());

            fileImageMapper.updateBoardImage(fileBoardImage);
        }

        boardMapper.setUpdate(board);

    }
    public void deleteCategoryMenus(ManageCategorySaveForm form){

        List<Integer> deleteList = form.getDeleteCategoryId();
        String boardUri = form.getBoardUri();

        if(deleteList == null)
            return;

        Map<String,Object> map = new HashMap<>();
        map.put("deleteList",deleteList);
        map.put("boardUri",boardUri);

        categoryMapper.setDeleteCategoryList(map);
    }

    // 게시글 관리 리스트 불러오기 검색 + 페이징
    public List<Post> getPostList(ManagePostForm form,Model model){

        Map<String,Object> map = new HashMap<>();
        map.put("keyword",form.getKeyword());
        map.put("boardUri",form.getBoardUri());
        map.put("perPageSize",form.getPerPageSize());
        map.put("beginPage",(form.getPage()-1)*form.getPerPageSize());
        map.put("memberId",form.getKeyword());

        int totalMember = postMapper.getTotalBoardPosts(map);
        PageHandler pageHandler = new PageHandler(form.getPage(),form.getPerPageSize(),totalMember);
        model.addAttribute("page",pageHandler);
        model.addAttribute("keyword",form.getKeyword());

        List<Post> list = postMapper.getListWithSearch(map);

        return list;
    }

    //글관리 글 삭제
    public void setDeletePosts(ManageDeletePostForm form) {

        if(form.getChkPostId() == null){
            return;
        }

        Map<String,Object> map = new HashMap<>();

        ArrayList<ManageDeletePost> deleteList = new ArrayList<>();
        for(int i= 0; i < form.getChkPostId().length;i++){
            deleteList.add(new ManageDeletePost(form.getChkPostId()[i],form.getBoardUri()[0]));
        }

        map.put("deleteList",deleteList);

        manageMapper.manageDeleteListPost(map);
    }

    /* 삭제된 글 리스트 불러오기 */


    public List<Post> getDeletedPostList(ManageDeletePostListForm form,Model model){


        Map<String,Object> map = new HashMap<>();
        map.put("boardUri",form.getBoardUri());
        map.put("perPageSize",form.getPerPageSize());
        map.put("beginPage",(form.getPage()-1)*form.getPerPageSize());
        map.put("keyword", form.getKeyword());

        int totalMember = manageMapper.getDeletedPostCount(map);
        PageHandler pageHandler = new PageHandler(form.getPage(),form.getPerPageSize(),totalMember);
        model.addAttribute("page",pageHandler);
        model.addAttribute("keyword",form.getKeyword());

        log.info("deletedList: {}",form);
        log.info("totalMember: {}",totalMember);

        List<Post> list = manageMapper.manageGetDeletedPostListWithSearch(map);




        return list;
    }

        /* 서브 매니저 수 */
    public int getTotalSubManager(String boardUri){

       return boardRoleMapper.getSubManagerCount(boardUri);
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
