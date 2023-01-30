package shop.iamhyunjun.tunatalk.dto.user;

import lombok.Getter;

@Getter
public class UserSignupDto {
    private String userNickname;
    private String userEmail;
    private String userPw;
    private String userPwCheck;
    private String userImage;
}
