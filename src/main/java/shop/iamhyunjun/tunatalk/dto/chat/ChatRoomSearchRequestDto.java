package shop.iamhyunjun.tunatalk.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomSearchRequestDto {
    private String roomName;

    public ChatRoomSearchRequestDto(String roomName) {
        this.roomName = roomName;
    }
}
