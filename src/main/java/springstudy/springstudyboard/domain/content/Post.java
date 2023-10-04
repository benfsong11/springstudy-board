package springstudy.springstudyboard.domain.content;

import jakarta.persistence.*;
import lombok.*;
import springstudy.springstudyboard.domain.User;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post extends Content {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "hits", nullable = false)
    private Integer hits;

    public PostHitsLog createHitsLog(User user) {
        if (this.getUser().getId().equals(user.getId())) {
            return null;
        }

        System.out.println("PostHitsLog is created!");

        PostHitsLog log = new PostHitsLog();
        log.setPost(this);
        log.setUser(user);

        return log;
    }

    public void plusHits() {
        ++this.hits;
    }
}
