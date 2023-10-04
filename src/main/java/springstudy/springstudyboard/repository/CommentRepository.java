package springstudy.springstudyboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springstudy.springstudyboard.domain.User;
import springstudy.springstudyboard.domain.content.Comment;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public Comment save(Comment comment);
    public Optional<Comment> findById(Long id);
}
