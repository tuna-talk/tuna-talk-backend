package shop.iamhyunjun.tunatalk.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoomMessage;

import java.util.List;

@Repository
public interface ChatRoomMessageRepository extends JpaRepository<ChatRoomMessage, Long> {
    List<ChatRoomMessage> findAllByChatRoom(ChatRoom chatRoom);
}
