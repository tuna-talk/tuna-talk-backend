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
        userRepository.findById(userId).orElse(
                () -> new CheckApiException(ErrorCode.NOT_EQUALS_USER)
        );
    }
}
