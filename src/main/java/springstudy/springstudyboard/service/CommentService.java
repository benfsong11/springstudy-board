package springstudy.springstudyboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springstudy.springstudyboard.domain.content.Comment;
import springstudy.springstudyboard.domain.content.Post;
import springstudy.springstudyboard.dto.CommentDto;
import springstudy.springstudyboard.dto.PostDto;
import springstudy.springstudyboard.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void create(CommentDto dto) {
        Comment comment = new Comment();
        comment.setBody(dto.getBody());
        comment.setUser(dto.getUser());
        comment.setPost(dto.getPost());

        commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
