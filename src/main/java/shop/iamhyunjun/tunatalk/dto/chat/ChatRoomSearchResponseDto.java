package shop.iamhyunjun.tunatalk.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatRoomSearchResponseDto {
    private Long chatRoomId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String roomName;

    public ChatRoomSearchResponseDto(ChatRoom chatRoom) {
        this.roomName = roomName;
    }
}
