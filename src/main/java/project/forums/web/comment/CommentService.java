package project.forums.web.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.forums.domain.comment.CommentMapper;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;


}
