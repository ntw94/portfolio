package project.forums.web.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import project.forums.domain.post.Post;
import project.forums.domain.post.PostMapper;
import project.forums.web.board.PageHandler;
import project.forums.web.post.form.PostSaveForm;
import project.forums.web.post.form.PostUpdateForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostMapper postMapper;

    public List<Post> getPosts(String boardUri, int page, int perPageSize, Model model){
        int totalPost = postMapper.getTotalPosts(boardUri);
        PageHandler pageHandler = new PageHandler(page,perPageSize,totalPost);

        model.addAttribute("page",pageHandler);

        Map<String, Object> map= new HashMap<>();
        map.put("boardUri",boardUri);
        map.put("perPageSize",perPageSize);
        map.put("beginPage",(page-1)*perPageSize);



        return postMapper.getListAll(map);
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

}
