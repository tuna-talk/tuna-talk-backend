package shop.iamhyunjun.tunatalk.service.Friend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.iamhyunjun.tunatalk.config.exception.CheckApiException;
import shop.iamhyunjun.tunatalk.config.exception.ErrorCode;
import shop.iamhyunjun.tunatalk.config.security.UserDetailsImpl;
import shop.iamhyunjun.tunatalk.dto.friend.FriendRequestDto;
import shop.iamhyunjun.tunatalk.dto.friend.FriendResponseDto;
import shop.iamhyunjun.tunatalk.entity.friend.Friend;
import shop.iamhyunjun.tunatalk.entity.user.User;
import shop.iamhyunjun.tunatalk.repository.friend.FriendRepository;
import shop.iamhyunjun.tunatalk.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FriendService {

    private final FriendRepository friendRepository;

    private final UserRepository userRepository;

    public FriendResponseDto addFriend(String userEmail, FriendRequestDto friendRequestDto) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_USER)
        );

        Friend friend = new Friend(user, friendRequestDto);

        if (userRepository.findByUserEmail(friendRequestDto.getFriendEmail()).isPresent()){
            friendRepository.save(friend);
        } else {
            throw new CheckApiException(ErrorCode.NOT_EXISTS_USER);
        }

        return new FriendResponseDto(friend);
    }

    public FriendResponseDto searchFriend(String userEmail, String friendEmail) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_USER)
        );

        Friend friend = friendRepository.findByFriendEmail(friendEmail).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_USER)
        );

        return new FriendResponseDto(friend);


//        List<Friend> friendList = friendRepository.findAll();
//        List<FriendRequestDto> friendRequestDtoList = new ArrayList<>();
//        for (Friend friend : friendList){
//            if (friend.getFriendEmail().equals(friendEmail)){
//                friendRequestDtoList.add(new FriendRequestDto(friend));
//            }
//        }
    }

    public String deleteFriend(String userEmail, String friendEmail) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_USER)
        );

        friendRepository.deleteFriendByFriendEmail(friendEmail);

        return "친구 삭제";
    }
}
