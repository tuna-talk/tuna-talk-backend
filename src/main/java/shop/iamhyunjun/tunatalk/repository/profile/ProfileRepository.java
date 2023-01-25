package shop.iamhyunjun.tunatalk.repository.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.iamhyunjun.tunatalk.entity.user.User;

public interface ProfileRepository extends JpaRepository<User, Long> {
}
