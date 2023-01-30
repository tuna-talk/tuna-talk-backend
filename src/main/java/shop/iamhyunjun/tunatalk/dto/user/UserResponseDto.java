package shop.iamhyunjun.tunatalk.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.iamhyunjun.tunatalk.entity.user.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String data;

    private int statuscode;

    private String userEmail;

    private String userNickname;

    public UserResponseDto(String data, int statuscode, User user) {
        this.data = data;
        this.statuscode = statuscode;
        this.userEmail = user.getUserEmail();
        this.userNickname = user.getUserNickname();

    }

    public UserResponseDto(String data, int statuscode) {
        this.data = data;
        this.statuscode = statuscode;
    }
}
