package springstudy.springstudyboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springstudy.springstudyboard.domain.content.PostHitsLog;
import springstudy.springstudyboard.repository.PostHitsLogRepository;

@Service
public class PostHitsLogService {

    private final PostHitsLogRepository postHitsLogRepository;

    @Autowired
    public PostHitsLogService(PostHitsLogRepository postHitsLogRepository) {
        this.postHitsLogRepository = postHitsLogRepository;
    }

    public void save(PostHitsLog log) {
        postHitsLogRepository.save(log);
    }
}
