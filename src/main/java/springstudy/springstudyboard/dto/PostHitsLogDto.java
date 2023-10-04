package springstudy.springstudyboard.dto;

import lombok.Getter;
import lombok.Setter;
import springstudy.springstudyboard.domain.User;
import springstudy.springstudyboard.domain.content.Post;

@Getter
@Setter
public class PostHitsLogDto {

    private Post post;
    private User user;
}
