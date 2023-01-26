package shop.iamhyunjun.tunatalk.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoomMessage;

@Getter
@NoArgsConstructor
public class ChatRoomRealResponseDto {
    private Long chatRoomMessageId;
    private String userEmail;
    private String userNickname;
    private String message;

    public ChatRoomRealResponseDto(ChatRoomMessage chatRoomMessage) {
        this.chatRoomMessageId = chatRoomMessage.getChatRoomMessageId();
        this.userEmail = chatRoomMessage.getSender().getUserEmail();
        this.userNickname = chatRoomMessage.getSender().getUserNickname();
        this.message = chatRoomMessage.getMessage();
    }
}
