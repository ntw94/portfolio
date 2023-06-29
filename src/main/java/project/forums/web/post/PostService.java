package project.forums.web.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.forums.domain.post.Post;
import project.forums.domain.post.PostMapper;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostMapper postMapper;

    public List<Post> getPosts(String boardUri){
        return postMapper.getListAll(boardUri);
    }
    public Post getPostOne(Post post){
        return postMapper.getListOne(post);
    }

    public int postSave(Post post){
        return postMapper.setInsert(post);
    }
    public int postUpdate(Post post){
        return postMapper.setUpdate(post);
    }
    public int postDelete(Post post){
        return postMapper.setDelete(post);
    }

}
