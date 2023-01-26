package shop.iamhyunjun.tunatalk.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shop.iamhyunjun.tunatalk.entity.friend.Friend;
@Getter
@AllArgsConstructor
public class FriendResponseDto {
    private Long id;
    private String friendEmail;
    public FriendResponseDto(Friend friend) {
        this.id = friend.getId();
        this.friendEmail = friend.getFriendEmail();
    }
}
