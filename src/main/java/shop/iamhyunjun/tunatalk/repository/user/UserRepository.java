package shop.iamhyunjun.tunatalk.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.iamhyunjun.tunatalk.entity.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    Optional<User> findByUserEmail(String userEmail);
}
