package shop.iamhyunjun.tunatalk.entity.friend;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.dto.friend.FriendRequestDto;
import shop.iamhyunjun.tunatalk.entity.user.User;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Friend {
    @Id
    @GeneratedValue
    @Column(name = "friend_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String friendEmail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Friend(User user, FriendRequestDto friendRequestDto) {
        this.user = user;
        this.friendEmail = friendRequestDto.getFriendEmail();
        this.id = friendRequestDto.getId();
    }
}
