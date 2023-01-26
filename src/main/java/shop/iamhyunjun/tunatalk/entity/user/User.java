package shop.iamhyunjun.tunatalk.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.dto.user.UserRequestDto;

import javax.persistence.*;

@Entity(name = "USERS")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = true)
    private String userNickname;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = true)
    private String userPwCheck;

    @Column(nullable = true)
    private String userMessage;

    @Column(nullable = true)
    private String userImage;

    public User(String userNickname, String userPw, String userEmail) {
        this.userNickname = userNickname;
        this.userPw = userPw;
        this.userEmail = userEmail;
    }

    public void update(UserRequestDto userRequestDto) {
        this.userNickname = userRequestDto.getUserNickname();
        this.userMessage = userRequestDto.getUserMessage();
    }

    public String imageUpdate(String userRequestDto) {
        this.userImage = userRequestDto;
        return userRequestDto;
    }
}
