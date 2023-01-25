package shop.iamhyunjun.tunatalk.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shop.iamhyunjun.tunatalk.entity.user.User;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    private String userImage;
    private String userNickname;
    private String userMessage;

    public UserRequestDto(User user) {
        this.userImage = user.getUserImage();
        this.userMessage = user.getUserMessage();
        this.userNickname = user.getUserNickname();
    }
}
