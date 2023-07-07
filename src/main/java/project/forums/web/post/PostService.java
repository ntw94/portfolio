package project.forums.web.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.forums.domain.category.PostCategory;
import project.forums.domain.category.PostCategoryMapper;
import project.forums.domain.post.Post;
import project.forums.domain.post.PostMapper;
import project.forums.web.board.PageHandler;
import project.forums.web.post.form.PostListForm;
import project.forums.web.post.form.PostSaveForm;
import project.forums.web.post.form.PostUpdateForm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostMapper postMapper;
    private final PostCategoryMapper postCategoryMapper;

    public List<PostListForm> getPosts(String boardUri,
                                       String category,
                                       int page,
                                       int perPageSize,
                                       Model model){

        int totalPost = postMapper.getTotalPosts(boardUri,category);
        PageHandler pageHandler = new PageHandler(page,perPageSize,totalPost);

        model.addAttribute("page",pageHandler);

        Map<String, Object> map= new HashMap<>();
        map.put("boardUri",boardUri);
        map.put("perPageSize",perPageSize);
        map.put("beginPage",(page-1)*perPageSize);
        map.put("category",category);

        List<Post> list = postMapper.getListAll(map);
        List<PostListForm> listForm = new ArrayList<>();
        LocalDateTime nowTime = LocalDateTime.now();

        convertListForm(list, listForm, nowTime);

        return listForm;
    }



    public Post getPostOne(String boardUri, Integer postId){
        Post post = new Post();
        post.setBoardUri(boardUri);
        post.setId(postId);

        return postMapper.getListOne(post);
    }

    public int postSave(Post post){

        return postMapper.setInsert(post);
    }

    public int postUpdate(PostUpdateForm postUpdateForm,String boardUri,Integer postId){

        Post oldPost = getPostOne(boardUri,postId);

        oldPost.setPostTitle(postUpdateForm.getPostTitle());
        oldPost.setPostContent(postUpdateForm.getPostContent());


        return postMapper.setUpdate(oldPost);
    }

    public int postDelete(String loginId, String boardUri, Integer postId){
        Post deletePost = getPostOne(boardUri,postId);

        if(loginId.equals(deletePost.getPostWriter())){
            return postMapper.setDelete(deletePost);
        }
        return 0;
    }

    public void postViewsUP(String boardUri, Integer postId){
        Post post = getPostOne(boardUri,postId);
        postMapper.viewsUp(post);
    }

    public List<PostCategory> getPostCategories(String boardUri){
        return postCategoryMapper.getListAll(boardUri);
    }

    public List<PostListForm> getNoticePosts(String boardUri){
       List<Post> list =  postMapper.getNoticePosts(boardUri);
       List<PostListForm> listForm = new ArrayList<>();
       LocalDateTime nowTime = LocalDateTime.now();

       convertListForm(list,listForm,nowTime);

        log.info("{}",listForm);
       return listForm;
    }



    private static void convertListForm(List<Post> list, List<PostListForm> listForm, LocalDateTime nowTime) {
        DateTimeFormatter newPattern;
        for (Post post : list) {
            PostListForm postListForm = new PostListForm();
            postListForm.setId(post.getId());
            postListForm.setBoardUri(post.getBoardUri());
            postListForm.setPostTitle(post.getPostTitle());
            postListForm.setPostWriter(post.getPostWriter());
            postListForm.setPostContent(post.getPostContent());
            postListForm.setPostHit(post.getPostHit());
            postListForm.setPostCategory(post.getPostCategory());

            if(ChronoUnit.DAYS.between(post.getPostRegiDate(), nowTime) == 0){
                newPattern = DateTimeFormatter.ofPattern("HH:mm");
            }else{
                newPattern = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            }
            postListForm.setPostRegiDate(post.getPostRegiDate().format(newPattern));

            listForm.add(postListForm);
        }
    }
}
