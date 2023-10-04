package springstudy.springstudyboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springstudy.springstudyboard.domain.User;
import springstudy.springstudyboard.dto.UserDto;
import springstudy.springstudyboard.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원가입
     */
    public void join(User user) {
        // 같은 이름의 중복 회원은 X
        validateDuplicatedUser(user);

        userRepository.save(user);
    }

    /**
     * 전체 회원 조회
     */
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User signInByUsername(UserDto req) {
        Optional<User> loginUser = userRepository.findByUsername(req.getUsername());

        if (loginUser.isEmpty()) {
            return null;
        }

        User user = loginUser.get();

        if(!user.getPassword().equals(req.getPassword())) {
            return null;
        }

        return user;
    }

    private void validateDuplicatedUser(User user) {
        userRepository.findByUsername(user.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
