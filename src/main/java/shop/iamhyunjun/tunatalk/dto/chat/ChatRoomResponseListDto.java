package shop.iamhyunjun.tunatalk.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatRoomResponseListDto {
    private ChatRoomResponseUserDto chatRoom;
    private List<ChatRoomMessagesResponseDto> chats;

    public ChatRoomResponseListDto(ChatRoomResponseUserDto chatRoomResponseUserDto, List<ChatRoomMessagesResponseDto> chatRoomMessagesResponseDtos) {
        this.chatRoom = chatRoomResponseUserDto;
        this.chats = chatRoomMessagesResponseDtos;
    }
}
