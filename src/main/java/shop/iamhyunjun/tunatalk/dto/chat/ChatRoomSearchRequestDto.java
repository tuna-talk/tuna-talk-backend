package shop.iamhyunjun.tunatalk.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomSearchRequestDto {
    private String friendNickname;

    public ChatRoomSearchRequestDto(String roomName) {
        this.friendNickname = roomName;
    }
}
