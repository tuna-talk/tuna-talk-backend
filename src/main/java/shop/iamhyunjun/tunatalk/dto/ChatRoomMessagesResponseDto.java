package shop.iamhyunjun.tunatalk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.entity.ChatRoom;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatRoomMessagesResponseDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String userName;
    private String userNickname;
    private String message;

    public ChatRoomMessagesResponseDto(ChatRoom chatRoom) { // + Sender
        this.createdAt = chatRoom.getCreatedAt();
        this.modifiedAt = chatRoom.getModifiedAt();
        this.userName = userName;
        this.userNickname = userNickname;
        this.message = message;
    }
}
