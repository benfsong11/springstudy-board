package springstudy.springstudyboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springstudy.springstudyboard.domain.content.PostHitsLog;

import java.time.LocalDateTime;
import java.util.List;

public interface PostHitsLogRepository extends JpaRepository<PostHitsLog, Long> {

    public PostHitsLog save(PostHitsLog log);
    List<PostHitsLog> findAllByCreatedAtAfter(LocalDateTime createdAt);
}
