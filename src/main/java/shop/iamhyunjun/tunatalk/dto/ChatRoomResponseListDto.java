package shop.iamhyunjun.tunatalk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChatRoomResponseListDto {
    private ChatRoomResponseDto chatRoomResponseDto;
    private List<ChatRoomMessagesResponseDto> chatRoomMessagesResponseDtos;

    public ChatRoomResponseListDto(ChatRoomResponseDto chatRoomResponseDto, List<ChatRoomMessagesResponseDto> chatRoomMessagesResponseDtos) {
        this.chatRoomResponseDto = chatRoomResponseDto;
        this.chatRoomMessagesResponseDtos = chatRoomMessagesResponseDtos;
    }
}
