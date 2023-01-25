package shop.iamhyunjun.tunatalk.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.dto.profile.ProfileRequestDto;

import javax.persistence.*;

@Entity(name = "USERS")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String userNickname;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = true)
    private String userPwCheck;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = true)
    private String userMessage;

    @Column(nullable = true)
    private String userImage;

    public User(String userNickname, String userName, String userPw, String userEmail) {
        this.userNickname = userNickname;
        this.userName = userName;
        this.userPw = userPw;
        this.userEmail = userEmail;
    }

    public void updateProfile(ProfileRequestDto profileRequestDto) {
        this.userImage = profileRequestDto.getUserImage();
        this.userMessage = profileRequestDto.getUserMessage();
        this.userNickname = profileRequestDto.getUserNickname();
    }
}
