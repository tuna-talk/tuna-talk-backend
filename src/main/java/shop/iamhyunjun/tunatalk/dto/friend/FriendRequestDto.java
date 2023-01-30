package shop.iamhyunjun.tunatalk.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.entity.friend.Friend;

import java.security.PrivateKey;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequestDto {
    private Long id;

    private String friendEmail;

    private String friendNickname;

    private String friendImage;

    private String friendMessage;

    public FriendRequestDto(Friend friend) {
        this.friendEmail = friend.getFriendEmail();
    }
}
