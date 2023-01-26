package shop.iamhyunjun.tunatalk.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.iamhyunjun.tunatalk.config.exception.CheckApiException;
import shop.iamhyunjun.tunatalk.config.exception.ErrorCode;
import shop.iamhyunjun.tunatalk.config.jwt.JwtUtil;
import shop.iamhyunjun.tunatalk.dto.user.UserLoginDto;
import shop.iamhyunjun.tunatalk.dto.user.UserRequestDto;
import shop.iamhyunjun.tunatalk.dto.user.UserSignupDto;
import shop.iamhyunjun.tunatalk.entity.user.User;
import shop.iamhyunjun.tunatalk.repository.user.UserRepository;

import javax.servlet.http.HttpServletResponse;
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

    // 회원가입
    public void signup(UserSignupDto userSignUpDto) {
        String userNickname = userSignUpDto.getUserNickname();
        String userPw = userSignUpDto.getUserPw();
        String userPwCheck = userSignUpDto.getUserPwCheck();
        String userEmail = userSignUpDto.getUserEmail();

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

        User user = new User(userNickname, userPw, userEmail);
        log.info("전");
        userRepository.save(user);
        log.info("후");
    }

    public void login(UserLoginDto userLoginDto, HttpServletResponse response) {
        String userName = userLoginDto.getUserEmail();
        String userPw = userLoginDto.getUserPw();

        // 아이디 일치 여부
        User user = userRepository.findByUserEmail(userName).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_USER)
        );
        // 비밀번호 일치 여부
        if(!passwordEncoder.matches(userPw, user.getUserPw())){
            throw new CheckApiException(ErrorCode.NOT_EQUALS_PASSWORD);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUserEmail()));
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
}
