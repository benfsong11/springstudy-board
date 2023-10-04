package springstudy.springstudyboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springstudy.springstudyboard.domain.User;
import springstudy.springstudyboard.dto.UserDto;
import springstudy.springstudyboard.service.UserService;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/board/user")
public class UserController {
    private final UserService userService;

    @Autowired
    // 스프링 컨테이너 안의 MemberService 객체를 자동으로 연결해 준다.
    // MemberService는 Annotation도 없는 순수한 자바 코드이기 때문에 스프링이 알 방법이 없다.
    // 따라서 처음 이 상태로 실행을 하면 memberService를 찾을 수 없다는 에러 메시지가 출력된다.
    // 그러므로 MemberService에 @Service를 붙여야 한다.
    // Controller와 Repository도 마찬가지로 @Controller, @Repository를 붙여야 한다.
    // Dependency Injection의 대표적인 예시
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "user/signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@RequestBody UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        userService.join(user);

        return "redirect:/board/board";
    }

    @PostMapping("/signIn")
    public User signIn(@RequestBody UserDto dto) {
        return userService.signInByUsername(dto);
    }
}
