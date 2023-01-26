package shop.iamhyunjun.tunatalk.repository.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoomUsers;
import shop.iamhyunjun.tunatalk.entity.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomUsersRepository extends JpaRepository<ChatRoomUsers, Long> {
    //List<ChatRoomUsers> findAllByChatRoomAndUser
    List<ChatRoomUsers> findAllByRoomUser(User user);
    //Optional<ChatRoomUsers> findByChatRoomAndRoomUser(ChatRoom chatRoom, User roomUser);

    //List<ChatRoomUsers> findAllByChatRoom_RoomNameAndRoomUser(String roomName, User roomUser);
}