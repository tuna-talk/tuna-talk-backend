package shop.iamhyunjun.tunatalk.repository.friend;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.iamhyunjun.tunatalk.entity.friend.Friend;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
        Optional<Friend> findByFriendEmail(String friendEmail);

        Optional<Friend> deleteFriendByFriendEmail(String friendEmail);

        List<Friend> findAllByFriendEmail(String friendEmail);
}
