package shop.iamhyunjun.tunatalk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.entity.ChatRoom;

@Getter
@NoArgsConstructor
public class ChatRoomResponseDto {
    private Long chatRoomId;
    private String roomName;

    public ChatRoomResponseDto(ChatRoom chatRoom) {
        this.chatRoomId = chatRoom.getChatRoomId();
        this.roomName = chatRoom.getRoomName();
    }
}
