package project.forums.web.post;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.member.Member;
import project.forums.domain.post.Post;
import project.forums.web.login.LoginService;
import project.forums.web.post.form.PostSaveForm;
import project.forums.web.post.form.PostUpdateForm;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards")
@Controller
public class PostController {

    private final LoginService loginService;
    private final PostService postService;

    @GetMapping("/{boardUri}/write")
    public String postWriteForm(HttpServletRequest request, Model model, @PathVariable String boardUri){
        sessionCheck(request,model);

        return "post/write";
    }
    @PostMapping("/{boardUri}/write")
    public String postSave(@ModelAttribute PostSaveForm postSaveForm, BindingResult bindingResult,
                           @PathVariable String boardUri){

        log.info("postSaveForm={}",postSaveForm);

        Post post = new Post();
        post.setBoardUri(boardUri);
        post.setPostTitle(postSaveForm.getPostTitle());
        post.setPostWriter(postSaveForm.getPostWriter());
        post.setPostContent(postSaveForm.getPostContent());

        postService.postSave(post);

        return "redirect:/boards/{boardUri}";
    }

    //글 상세보기
    @GetMapping("/{boardUri}/{postId}")
    public String postView(HttpServletRequest request, Model model, @PathVariable String boardUri,@PathVariable Integer postId){
        sessionCheck(request,model);

        Post post = new Post();
        post.setId(postId);
        post.setBoardUri(boardUri);

        Post findPost = postService.getPostOne(post);
        model.addAttribute("post",findPost);
        log.info("viewPost= {}",findPost);

        return "post/view";
    }
    //글 수정
    @GetMapping("/{boardUri}/{postId}/edit")
    public String postUpdateForm(
            HttpServletRequest request,
            Model model,
            @PathVariable String boardUri,
            @PathVariable Integer postId
    ){
        sessionCheck(request,model);

        Post post = new Post();
        post.setId(postId);
        post.setBoardUri(boardUri);

        Post findPost = postService.getPostOne(post);
        model.addAttribute("post",findPost);

        log.info("viewPost= {}",findPost);

        return "post/edit";
    }
    @PostMapping("/{boardUri}/{postId}/edit")
    public String postUpdate(@ModelAttribute PostUpdateForm postUpdateForm, BindingResult bindingResult){


        return "redirect:/boards/{boardUri}";
    }

    //세션체크
    private void sessionCheck(HttpServletRequest request, Model model) {
        Member loginMember = loginService.sessionCheck(request);
        log.info("loginMem11ber = {}",loginMember);

        if(loginMember != null){
            model.addAttribute("member",loginMember);
        }
    }
}
