package shop.iamhyunjun.tunatalk.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shop.iamhyunjun.tunatalk.entity.friend.Friend;
@Getter
@AllArgsConstructor
public class FriendResponseDto {
    private Long id;
    private String friendEmail;
    private String friendNickname;
    private String friendImage;
    private String friendMessage;
    public FriendResponseDto(Friend friend, String friendNickname, String friendImage, String friendMessage) {
        this.id = friend.getId();
        this.friendEmail = friend.getFriendEmail();
        this.friendNickname = friendNickname;
        this.friendImage = friendImage;
        this.friendMessage = friendMessage;
    }
}
