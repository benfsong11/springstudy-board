package springstudy.springstudyboard.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springstudy.springstudyboard.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public User save(User user);
    public Optional<User> findById(Long id);
    public Optional<User> findByUsername(String username);
}
