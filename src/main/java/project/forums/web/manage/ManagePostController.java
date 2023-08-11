package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.post.Post;
import project.forums.web.manage.form.ManageDeletePostForm;
import project.forums.web.manage.form.ManagePostForm;
import project.forums.web.manage.form.ManagePostListForm;
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

        //팝업 방식으로 지울것
        log.info("글 : {} "  ,list);

        return "manage/post/post";
    }
    //선택된 글 삭제하기
    @PostMapping("/post/{boardUri}/delete")
    public String managePostDelete(@ModelAttribute ManageDeletePostForm form, @PathVariable String boardUri){
        log.info("{}",form);
        manageService.setDeletePosts(form);

        return "redirect:/manage/post/{boardUri}";
    }

    //삭제한 글 보기
    @GetMapping("/post/deleted/post/{boardUri}")
    public String managePostDeletedPost(@ModelAttribute ManagePostForm form, @PathVariable String boardUri,Model model){

        List<Post> list = manageService.getPostList(form,model);
        model.addAttribute("list",list);

        return "manage/post/post-deleted-post";
    }

    //삭제한 댓글 보기
    @GetMapping("/post/deleted/comment/{boardUri}")
    public String managePostDeletedComment(@PathVariable String boardUri){

        return "manage/post/post-deleted-comment";
    }
}
