package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.post.Post;
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
    @PostMapping("/post/{boardUri}/delete")
    public String managePostDelete(@ModelAttribute ManagePostForm form,@PathVariable String boardUri){


        return "redirect:/manage/post/{boardUri}";
    }

    @GetMapping("/post/deleted/post/{boardUri}")
    public String managePostDeletedPost(@ModelAttribute ManagePostForm form, @PathVariable String boardUri,Model model){

        List<Post> list = manageService.getPostList(form,model);
        model.addAttribute("list",list);

        return "manage/post/post-deleted-post";
    }

    @GetMapping("/post/deleted/comment/{boardUri}")
    public String managePostDeletedComment(@PathVariable String boardUri){

        return "manage/post/post-deleted-comment";
    }
}
