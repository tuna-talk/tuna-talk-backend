package shop.iamhyunjun.tunatalk.dto.profile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import shop.iamhyunjun.tunatalk.entity.user.User;
@Getter
@RequiredArgsConstructor
public class ProfileResponseDto {
    private String userImage;
    private String userMessage;
    private String userNickname;

    public ProfileResponseDto(User user) {
        this.userImage = user.getUserImage();
        this.userMessage = user.getUserMessage();
        this.userNickname = user.getUserNickname();
    }
}
