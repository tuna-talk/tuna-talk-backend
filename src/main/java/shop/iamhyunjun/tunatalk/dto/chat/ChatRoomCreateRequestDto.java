package shop.iamhyunjun.tunatalk.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomCreateRequestDto {
    private String roomName;
    private String friendEmail; //

    public ChatRoomCreateRequestDto(String roomName) {
        this.roomName = roomName;
    }
}
