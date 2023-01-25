package shop.iamhyunjun.tunatalk.service.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.iamhyunjun.tunatalk.config.exception.CheckApiException;
import shop.iamhyunjun.tunatalk.config.exception.ErrorCode;
import shop.iamhyunjun.tunatalk.dto.profile.ProfileRequestDto;
import shop.iamhyunjun.tunatalk.dto.profile.ProfileResponseDto;
import shop.iamhyunjun.tunatalk.entity.user.User;
import shop.iamhyunjun.tunatalk.repository.user.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {
    private final UserRepository userRepository;
    public ProfileResponseDto updateProfile(Long userId, ProfileRequestDto profileRequestDto, User user) {
        // 유저가 같지 않으면 에러코드 전송
        userRepository.findById(userId).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EQUALS_USER)
        );

        if (user.getId().equals(userId)){
            user.updateProfile(profileRequestDto);
        }

        return new ProfileResponseDto(user);
    }
}
