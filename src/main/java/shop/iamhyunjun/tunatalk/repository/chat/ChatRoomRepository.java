package shop.iamhyunjun.tunatalk.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByChatRoomId(Long chatRoomId);
    List<ChatRoom> findAllByRoomName(String roomName);
    //List<ChatRoom> findAllByRoomNameAnd
}
