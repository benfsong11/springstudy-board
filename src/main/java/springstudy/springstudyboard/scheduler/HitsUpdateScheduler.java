package springstudy.springstudyboard.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springstudy.springstudyboard.domain.content.Post;
import springstudy.springstudyboard.domain.content.PostHitsLog;
import springstudy.springstudyboard.repository.PostHitsLogRepository;
import springstudy.springstudyboard.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class HitsUpdateScheduler {

    private final PostRepository postRepository;
    private final PostHitsLogRepository postHitsLogRepository;

    @Autowired
    public HitsUpdateScheduler(PostRepository postRepository, PostHitsLogRepository postHitsLogRepository) {
        this.postRepository = postRepository;
        this.postHitsLogRepository = postHitsLogRepository;
    }

    @Scheduled(fixedRate = 600000)
    @Transactional
    public void updateHits() {
        List<PostHitsLog> logs = postHitsLogRepository.findAllByCreatedAtAfter(LocalDateTime.now().minusMinutes(10));

        for (PostHitsLog log : logs) {
            Post post = postRepository.findById(log.getPost().getId()).orElse(null);

            if (post != null) {
                post.plusHits();

                postRepository.save(post);
            }
        }
    }
}
