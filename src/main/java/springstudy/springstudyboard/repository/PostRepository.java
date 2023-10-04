package springstudy.springstudyboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springstudy.springstudyboard.domain.content.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    public Post save(Post post);
    public Optional<Post> findById(Long id);
}
