package shop.iamhyunjun.tunatalk.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.iamhyunjun.tunatalk.config.security.UserDetailsImpl;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomResponseDto;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomSearchRequestDto;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomSearchResponseDto;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoomUsers;
import shop.iamhyunjun.tunatalk.entity.user.User;
import shop.iamhyunjun.tunatalk.repository.chat.ChatRoomRepository;
import shop.iamhyunjun.tunatalk.repository.chat.ChatRoomUsersRepository;
import shop.iamhyunjun.tunatalk.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomSearchService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomUsersRepository chatRoomUsersRepository;
    private final UserRepository userRepository;
    public List<ChatRoomSearchResponseDto> searchChatRoom(ChatRoomSearchRequestDto chatRoomSearchRequestDto,
                                                          UserDetailsImpl userDetailsImpl) {

        List<ChatRoomUsers> chatRoomUsers = chatRoomUsersRepository.findAllByRoomUser(userDetailsImpl.getUser());

        List<ChatRoom> chatRooms = new ArrayList<>();

        for (ChatRoomUsers chatRoomUser : chatRoomUsers) {
            chatRooms.add(chatRoomUser.getChatRoom());
        }

        List<ChatRoomSearchResponseDto> chatRoomSearchResponseDtos = new ArrayList<>();
        for (ChatRoom chatRoom : chatRooms) {
            if (chatRoom.getUser1().getUserNickname().equals(chatRoomSearchRequestDto.getFriendNickname())
                    || chatRoom.getUser2().getUserNickname().equals(chatRoomSearchRequestDto.getFriendNickname())) {
                ChatRoomSearchResponseDto chatRoomSearchResponseDto = new ChatRoomSearchResponseDto(chatRoom, userDetailsImpl.getUser());
                chatRoomSearchResponseDtos.add(chatRoomSearchResponseDto);
            }
        }

        return chatRoomSearchResponseDtos;
    }
}
