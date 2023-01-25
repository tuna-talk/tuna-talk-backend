package shop.iamhyunjun.tunatalk.controller.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import shop.iamhyunjun.tunatalk.config.security.UserDetailsImpl;
import shop.iamhyunjun.tunatalk.dto.profile.ProfileRequestDto;
import shop.iamhyunjun.tunatalk.dto.profile.ProfileResponseDto;
import shop.iamhyunjun.tunatalk.service.profile.ProfileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
public class ProfileController {
    private ProfileService profileService;

    @PutMapping("/{userId}")
    public ProfileResponseDto updateProfile(@PathVariable Long userId,
                                            @RequestBody ProfileRequestDto profileRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return profileService.updateProfile(userId, profileRequestDto, userDetails.getUser());
    }
}
