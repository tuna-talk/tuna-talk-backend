package shop.iamhyunjun.tunatalk.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.iamhyunjun.tunatalk.config.security.UserDetailsImpl;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomSearchRequestDto;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomSearchResponseDto;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoomUsers;
import shop.iamhyunjun.tunatalk.repository.chat.ChatRoomRepository;
import shop.iamhyunjun.tunatalk.repository.chat.ChatRoomUsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomSearchService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomUsersRepository chatRoomUsersRepository;
    public List<ChatRoomSearchResponseDto> searchChatRoom(ChatRoomSearchRequestDto chatRoomSearchRequestDto,
                                                    UserDetailsImpl userDetailsImpl) {
        //List<ChatRoom> chatRooms = chatRoomRepository.findAllByRoomName(chatRoomSearchRequestDto.getRoomName());

        //List<ChatRoom> chatRooms1 = new ArrayList<>();

//        for (ChatRoom chatRoom : chatRooms) {
//            Optional<ChatRoomUsers> chatRoomUsers = chatRoomUsersRepository.findByChatRoomAndRoomUser(chatRoom, userDetailsImpl.getUser());
//            chatRooms1.add(chatRoomUsers.get().getChatRoom());
//        }

        List<ChatRoomUsers> chatRoomUsers = chatRoomUsersRepository.findAllByChatRoom_RoomNameAndRoomUser(chatRoomSearchRequestDto.getRoomName(), userDetailsImpl.getUser());

        List<ChatRoomSearchResponseDto> chatRoomSearchResponseDtos = new ArrayList<>();
        for (ChatRoomUsers chatRoomUser : chatRoomUsers) {
            ChatRoomSearchResponseDto chatRoomSearchResponseDto = new ChatRoomSearchResponseDto(chatRoomUser.getChatRoom());
            chatRoomSearchResponseDtos.add(chatRoomSearchResponseDto);
        }

        return chatRoomSearchResponseDtos;
    }
}
