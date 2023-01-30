package shop.iamhyunjun.tunatalk.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;
import shop.iamhyunjun.tunatalk.entity.user.User;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatRoomResponseDto {
    private Long chatRoomId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String friendNickname;
    private String lastMessage; //

    public ChatRoomResponseDto(ChatRoom chatRoom, User user) {
        this.chatRoomId = chatRoom.getChatRoomId();
        this.createdAt = chatRoom.getCreatedAt();
        this.modifiedAt = chatRoom.getModifiedAt();
        this.friendNickname = (chatRoom.getUser1().getUserEmail().equals(user.getUserEmail()))
                ? chatRoom.getUser2().getUserNickname() : chatRoom.getUser1().getUserNickname();
        this.lastMessage = chatRoom.getLastMessage();
    }
}
