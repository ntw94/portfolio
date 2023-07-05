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

        Post findPost = postService.getPostOne(boardUri,postId);
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

        Post findPost = postService.getPostOne(boardUri,postId);
        model.addAttribute("post",findPost);

        log.info("viewPost= {}",findPost);

        return "post/edit";
    }
    @PostMapping("/{boardUri}/{postId}/edit")
    public String postUpdate(@ModelAttribute PostUpdateForm postUpdateForm,
                             BindingResult bindingResult,
                             @PathVariable String boardUri,
                             @PathVariable Integer postId){

        postService.postUpdate(postUpdateForm,boardUri,postId);


        return "redirect:/boards/{boardUri}/{postId}";
    }

    @PostMapping("/{boardUri}/{postId}/delete")
    public String postDelete(
            HttpServletRequest request,
                            @PathVariable String boardUri,
                             @PathVariable Integer postId){
        String loginId = sessionCheck(request);
        postService.postDelete(loginId,boardUri,postId);


        return "redirect:/boards/{boardUri}";
    }

    //세션체크
    private int sessionCheck(HttpServletRequest request, Model model) {
        Member loginMember = loginService.sessionCheck(request);
        log.info("loginMember = {}",loginMember);

        if(loginMember != null){
            model.addAttribute("member",loginMember);
            return 1;
        }
        return 0;
    }
    private String sessionCheck(HttpServletRequest request) {
        Member loginMember = loginService.sessionCheck(request);
        log.info("loginMember = {}",loginMember);

        if(loginMember != null){
            return loginMember.getMemberId();
        }
        return null;
    }
}
