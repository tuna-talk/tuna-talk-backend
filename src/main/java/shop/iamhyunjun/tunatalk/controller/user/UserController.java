package shop.iamhyunjun.tunatalk.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.iamhyunjun.tunatalk.config.security.UserDetailsImpl;
import shop.iamhyunjun.tunatalk.dto.user.UserResponseDto;
import shop.iamhyunjun.tunatalk.dto.user.UserLoginDto;
import shop.iamhyunjun.tunatalk.dto.user.UserRequestDto;
import shop.iamhyunjun.tunatalk.dto.user.UserSignupDto;
import shop.iamhyunjun.tunatalk.service.s3.S3Uploader;
import shop.iamhyunjun.tunatalk.service.user.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final S3Uploader s3Uploader;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> signup(@RequestBody UserSignupDto userSignUpDto){
        userService.signup(userSignUpDto);
        String data = "회원가입 성공";
        return ResponseEntity.ok(new UserResponseDto(data, 200));
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response){
        userService.login(userLoginDto, response);
        String data = "로그인 성공";
        return ResponseEntity.ok(new UserResponseDto(data, 200));
    }

    @PatchMapping("/{userEmail}")
    @ResponseBody
    public UserRequestDto update(@PathVariable String userEmail,
                              @RequestBody UserRequestDto userRequestDto){
        return userService.update(userEmail, userRequestDto);
    }

    @PostMapping("/{userEmail}")
    @ResponseBody
    public String imageUpdate(@PathVariable String userEmail,
                                         @RequestParam("image")MultipartFile multipartFile) throws IOException{
        userService.imageUpdate(userEmail, multipartFile);
        String data = "이미지 업로드 성공";
        return userService.imageUpdate(userEmail, multipartFile);
    }

}
