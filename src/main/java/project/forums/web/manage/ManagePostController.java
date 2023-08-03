package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.forums.domain.post.Post;
import project.forums.web.manage.form.ManagePostForm;
import project.forums.web.manage.form.ManagePostListForm;
import project.forums.web.post.PostService;

import java.util.List;

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

        log.info("글 : {} ",list);

        return "manage/post/post";
    }

    @GetMapping("/post/deleted/post/{boardUri}")
    public String managePostDeletedPost(@PathVariable String boardUri){

        return "manage/post/post-deleted-post";
    }

    @GetMapping("/post/deleted/comment/{boardUri}")
    public String managePostDeletedComment(@PathVariable String boardUri){

        return "manage/post/post-deleted-comment";
    }
}
