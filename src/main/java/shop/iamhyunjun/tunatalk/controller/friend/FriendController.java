package shop.iamhyunjun.tunatalk.controller.friend;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import shop.iamhyunjun.tunatalk.config.security.UserDetailsImpl;
import shop.iamhyunjun.tunatalk.dto.friend.FriendRequestDto;
import shop.iamhyunjun.tunatalk.dto.friend.FriendResponseDto;
import shop.iamhyunjun.tunatalk.dto.friend.GetUserDto;
import shop.iamhyunjun.tunatalk.entity.friend.Friend;
import shop.iamhyunjun.tunatalk.service.Friend.FriendService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/")
    public GetUserDto getUser(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return friendService.getUser(userDetails.getUser());
    }

    @PostMapping("/{userEmail}")
    public FriendResponseDto addFriend(@PathVariable String userEmail,
                                       @RequestBody FriendRequestDto friendRequestDto){
        return friendService.addFriend(userEmail, friendRequestDto);
    }

    @GetMapping("/{userEmail}/{friendEmail}")
    public FriendResponseDto searchFriend(@PathVariable String userEmail,
                                          @PathVariable String friendEmail) {
        return friendService.searchFriend(userEmail, friendEmail);
    }

    @DeleteMapping("/{userEmail}/{friendEmail}")
    public String deleteFriend(@PathVariable String userEmail,
                               @PathVariable String friendEmail){
        return friendService.deleteFriend(userEmail, friendEmail);
    }
}