package shop.iamhyunjun.tunatalk.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetUserDto {
    private String userEmail;

    private String userNickname;

    private String userImage;

    private String userMessage;
}
