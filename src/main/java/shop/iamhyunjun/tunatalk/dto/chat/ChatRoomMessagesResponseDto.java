package shop.iamhyunjun.tunatalk.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoomMessage;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ChatRoomMessagesResponseDto {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String userNickname;
    private String message;

    public ChatRoomMessagesResponseDto(ChatRoom chatRoom, ChatRoomMessage chatRoomMessage) { // + Sender
        this.createdAt = chatRoom.getCreatedAt();
        this.modifiedAt = chatRoom.getModifiedAt();
        this.userNickname = chatRoomMessage.getSender().getUserNickname();
        this.message = chatRoomMessage.getMessage();
    }
}
