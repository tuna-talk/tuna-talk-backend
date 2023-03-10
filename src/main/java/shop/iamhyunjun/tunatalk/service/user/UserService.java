package shop.iamhyunjun.tunatalk.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import shop.iamhyunjun.tunatalk.config.exception.CheckApiException;
import shop.iamhyunjun.tunatalk.config.exception.ErrorCode;
import shop.iamhyunjun.tunatalk.config.jwt.JwtUtil;
import shop.iamhyunjun.tunatalk.dto.user.UserLoginDto;
import shop.iamhyunjun.tunatalk.dto.user.UserRequestDto;
import shop.iamhyunjun.tunatalk.dto.user.UserResponseDto;
import shop.iamhyunjun.tunatalk.dto.user.UserSignupDto;
import shop.iamhyunjun.tunatalk.entity.user.User;
import shop.iamhyunjun.tunatalk.repository.user.UserRepository;
import shop.iamhyunjun.tunatalk.service.s3.S3Uploader;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3Uploader s3Uploader;

    // 회원가입
    public void signup(UserSignupDto userSignUpDto) {
        String userNickname = userSignUpDto.getUserNickname();
        String userPw = userSignUpDto.getUserPw();
        String userPwCheck = userSignUpDto.getUserPwCheck();
        String userEmail = userSignUpDto.getUserEmail();
        String userImage = "https://s3.console.aws.amazon.com/s3/object/ggobsarikuna?region=ap-northeast-2&prefix=static/kakao_2.jpg";

        // 아이디 중복 검사
        Optional<User> userNameDuplicate = userRepository.findByUserEmail(userSignUpDto.getUserEmail());
        if (userNameDuplicate.isPresent()) {
            throw new CheckApiException(ErrorCode.EXISTS_USER);
        }

        // 비밀번호 일치 여부 확인
        if (!Objects.equals(userPw, userPwCheck)){
            throw new CheckApiException(ErrorCode.NOT_EQUALS_PASSWORD);
        } else {
            userPw = passwordEncoder.encode(userSignUpDto.getUserPw()); // 비밀번호 일치하면 인코딩
        }

        User user = new User(userNickname, userPw, userEmail, userImage);
        userRepository.save(user);
    }

    public UserResponseDto login(UserLoginDto userLoginDto, HttpServletResponse response) {
        String userName = userLoginDto.getUserEmail();
        String userPw = userLoginDto.getUserPw();
        String data = "로그인 성공";
        int statucode = 200;

        // 아이디 일치 여부
        User user = userRepository.findByUserEmail(userName).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_USER)
        );
        // 비밀번호 일치 여부
        if(!passwordEncoder.matches(userPw, user.getUserPw())){
            throw new CheckApiException(ErrorCode.NOT_EQUALS_PASSWORD);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUserEmail()));

        return new UserResponseDto(data, statucode, user.getUserEmail(), user.getUserNickname(), user.getUserImage());
    }

    public UserRequestDto update(String userEmail, UserRequestDto userRequestDto) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_USER)
        );

        if (user.getUserEmail().equals(userEmail)){
            user.update(userRequestDto);
        }

        return new UserRequestDto(user);
    }

    public String imageUpdate(String userEmail, MultipartFile multipartFile) throws IOException {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_USER)
        );

        if (user.getUserEmail().equals(userEmail)){
            s3Uploader.upload(multipartFile, "static");
            user.imageUpdate(s3Uploader.upload(multipartFile, "static"));
        }
        return user.imageUpdate(s3Uploader.upload(multipartFile, "static"));
    }
}
