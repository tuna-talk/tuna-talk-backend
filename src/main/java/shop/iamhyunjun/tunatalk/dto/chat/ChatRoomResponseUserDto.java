package shop.iamhyunjun.tunatalk.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;
import shop.iamhyunjun.tunatalk.entity.user.User;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatRoomResponseUserDto {
    private Long chatRoomId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String roomName;
    private String userName;
    private String userNickname;

    public ChatRoomResponseUserDto(ChatRoom chatRoom, User user) {
        this.chatRoomId = chatRoom.getChatRoomId();
        this.createdAt = chatRoom.getCreatedAt();
        this.modifiedAt = chatRoom.getModifiedAt();
        this.roomName = chatRoom.getRoomName();
        this.userName = user.getUserName();
        this.userNickname = user.getUserNickname();
    }
}
