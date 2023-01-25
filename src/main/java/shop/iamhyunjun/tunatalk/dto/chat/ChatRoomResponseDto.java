package shop.iamhyunjun.tunatalk.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatRoomResponseDto {
    private Long chatRoomId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String roomName;

    public ChatRoomResponseDto(ChatRoom chatRoom) {
        this.chatRoomId = chatRoom.getChatRoomId();
        this.createdAt = chatRoom.getCreatedAt();
        this.modifiedAt = chatRoom.getModifiedAt();
        this.roomName = chatRoom.getRoomName();
    }
}
