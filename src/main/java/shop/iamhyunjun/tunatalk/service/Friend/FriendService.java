package shop.iamhyunjun.tunatalk.service.Friend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.iamhyunjun.tunatalk.config.exception.CheckApiException;
import shop.iamhyunjun.tunatalk.config.exception.ErrorCode;
import shop.iamhyunjun.tunatalk.dto.friend.FriendRequestDto;
import shop.iamhyunjun.tunatalk.dto.friend.FriendResponseDto;
import shop.iamhyunjun.tunatalk.dto.friend.GetUserDto;
import shop.iamhyunjun.tunatalk.entity.friend.Friend;
import shop.iamhyunjun.tunatalk.entity.user.User;
import shop.iamhyunjun.tunatalk.repository.friend.FriendRepository;
import shop.iamhyunjun.tunatalk.repository.user.UserRepository;

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

        User findFriend = userRepository.findByUserEmail(friendRequestDto.getFriendEmail()).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_USER)
        );

        String friendNickname = findFriend.getUserNickname();
        String friendImage = findFriend.getUserImage();
        String friendMessage = findFriend.getUserMessage();

        Friend friend = new Friend(user, friendRequestDto, friendNickname, friendImage, friendMessage);

        friendRepository.save(friend);

        return new FriendResponseDto(friend, friendNickname, friendImage, friendMessage);
    }

    public FriendResponseDto searchFriend(String userEmail, String friendEmail) {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_USER)
        );

        User friend = userRepository.findByUserEmail(friendEmail).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_USER)
        );

        String friendEmails = friend.getUserEmail();
        String friendNickname = friend.getUserNickname();
        String friendImage = friend.getUserImage();
        String friendMessage = friend.getUserMessage();

        return new FriendResponseDto(friend, friendEmails, friendNickname, friendImage, friendMessage);


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

        return "?????? ??????";
    }

    public GetUserDto getUser(User user) {
        User findUser = userRepository.findByUserEmail(user.getUserEmail()).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_USER)
        );

        String userEmail = findUser.getUserEmail();
        String userNickname = findUser.getUserNickname();
        String userImage = findUser.getUserImage();
        String userMessage = findUser.getUserMessage();

        return new GetUserDto(userEmail, userNickname, userImage, userMessage);
    }
}
