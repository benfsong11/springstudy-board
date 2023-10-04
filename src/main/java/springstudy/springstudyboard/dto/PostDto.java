package springstudy.springstudyboard.dto;

import lombok.Getter;
import lombok.Setter;
import springstudy.springstudyboard.domain.User;

@Getter
@Setter
public class PostDto {
    private String title;
    private String body;
    private User user;
}
