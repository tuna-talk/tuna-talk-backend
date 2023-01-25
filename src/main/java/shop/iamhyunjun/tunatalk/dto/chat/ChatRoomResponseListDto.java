package shop.iamhyunjun.tunatalk.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatRoomResponseListDto {
    private ChatRoomResponseUserDto chatRoomResponseUserDto;
    private List<ChatRoomMessagesResponseDto> chatRoomMessagesResponseDtos;

    public ChatRoomResponseListDto(ChatRoomResponseUserDto chatRoomResponseUserDto, List<ChatRoomMessagesResponseDto> chatRoomMessagesResponseDtos) {
        this.chatRoomResponseUserDto = chatRoomResponseUserDto;
        this.chatRoomMessagesResponseDtos = chatRoomMessagesResponseDtos;
    }
}
