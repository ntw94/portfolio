package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.comment.Comment;
import project.forums.domain.post.Post;
import project.forums.web.manage.form.*;
import project.forums.web.post.PostService;

import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manage")
@Controller
public class ManagePostController {

    private final ManageService manageService;
    private final PostService postService;

    @GetMapping("/post/{boardUri}")
    public String managePostHome(@ModelAttribute ManagePostForm form, @PathVariable String boardUri, Model model){

        log.info("글관리log: {}",form);

        List<Post> list = manageService.getPostList(form,model);
        model.addAttribute("list",list);

        return "manage/post/post";
    }
    //선택된 글 삭제하기
    @PostMapping("/post/{boardUri}/delete")
    public String managePostDelete(@ModelAttribute ManageDeletePostForm form,
                                   @PathVariable String boardUri){
        log.info("{}",form);
        manageService.setDeletePosts(form);

        return "redirect:/manage/post/{boardUri}?searchMenu="+form.getSearchMenu()+"&keyword="+form.getKeyword();
    }

    //삭제한 글 리스트 보기
    @GetMapping("/post/deleted/post/{boardUri}")
    public String managePostDeletedPost(@ModelAttribute ManageDeletePostListForm form, @PathVariable String boardUri, Model model){

        List<Post> list = manageService.getDeletedPostList(form,model);
        model.addAttribute("list",list);

        return "manage/post/post-deleted-post";
    }

    /* 삭제한 게시글 복구처리 */
    @PostMapping("/post/deleted/post/{boardUri}/restore")
    public String managePostRestorePost(@ModelAttribute ManageRestorePostListForm form,
                                        @PathVariable String boardUri,
                                        Model model){

        log.info("restore: {} ", form);
        manageService.setRestorePosts(form);

        return "redirect:/manage/post/deleted/post/{boardUri}?searchMenu="+form.getSearchMenu()+"&keyword="+form.getKeyword();
    }

    /* 낱개로 게시글 복구 */
    @PostMapping("/post/deleted/post/{boardUri}/restore/{id}")
    public String managePostRestorePostOne(@ModelAttribute ManageRestorePostListForm form,
                                           @PathVariable String boardUri,
                                           @PathVariable Integer id){
        log.info("{}",form);
        log.info("{}",boardUri);
        manageService.setRestorePostOne(boardUri,id);

        return "redirect:/manage/post/deleted/post/{boardUri}?searchMenu="+form.getSearchMenu()+"&keyword="+form.getKeyword();
    }

    //삭제한 댓글 보기
    @GetMapping("/post/deleted/comment/{boardUri}")
    public String managePostDeletedComment(
            @ModelAttribute ManageDeletedCommentListForm form,
            @PathVariable String boardUri,
            Model model){

        log.info("{}",form);
        List<Comment> list =  manageService.getDeletedCommentList(form,model);
        model.addAttribute("list",list);

        return "manage/post/post-deleted-comment";
    }

    /* 댓글 하나 복구 */
    @PostMapping("/post/deleted/comment/{boardUri}/restore/{id}")
    public String manageCommentRestoreOne(
            @ModelAttribute ManageRestoreCommentForm form,
            @PathVariable String boardUri,
            @PathVariable Integer id,
            Model model){

        log.info("{}",form);
        manageService.setRestoreCommentOne(form);

        return "redirect:/manage/post/deleted/comment/{boardUri}";
    }

    /* 선택된 댓글 복구 */
    @PostMapping("/post/deleted/comment/{boardUri}/restore")
    public String manageCommentRestores(
            @ModelAttribute ManageRestoreCommentListForm form,
            @PathVariable String boardUri,
            Model model){

        log.info("{}",form);
        manageService.setRestoreComments(form);

        return "redirect:/manage/post/deleted/comment/{boardUri}";
    }
}
