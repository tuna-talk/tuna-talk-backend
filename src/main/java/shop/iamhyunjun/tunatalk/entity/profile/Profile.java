package shop.iamhyunjun.tunatalk.entity.profile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.dto.profile.ProfileRequestDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @Column(nullable = true)
    private String userNickname;

    @Column(nullable = true)
    private String userImage;

    @Column(nullable = true)
    private String userMessage;

    public void updateProfile(ProfileRequestDto profileRequestDto) {
        this.userImage = profileRequestDto.getUserImage();
        this.userMessage = profileRequestDto.getUserMessage();
        this.userNickname = profileRequestDto.getUserNickname();
    }
}
