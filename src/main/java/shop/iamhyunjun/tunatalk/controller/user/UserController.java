package shop.iamhyunjun.tunatalk.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.iamhyunjun.tunatalk.dto.user.ResponseDto;
import shop.iamhyunjun.tunatalk.dto.user.UserLoginDto;
import shop.iamhyunjun.tunatalk.dto.user.UserSignupDto;
import shop.iamhyunjun.tunatalk.service.user.UserService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> signup(@RequestBody UserSignupDto userSignUpDto){
        userService.signup(userSignUpDto);
        String data = "회원가입 성공";
        return ResponseEntity.ok(new ResponseDto(data, 200));
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response){
        userService.login(userLoginDto, response);
        String data = "로그인 성공";
        return ResponseEntity.ok(new ResponseDto(data, 200));
    }

//    @PutMapping("/{userId}")
//    @ResponseBody
//    public ResponseDto

}
