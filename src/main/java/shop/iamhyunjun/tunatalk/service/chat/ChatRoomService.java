package shop.iamhyunjun.tunatalk.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.iamhyunjun.tunatalk.config.security.UserDetailsImpl;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomMessagesResponseDto;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomResponseDto;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomResponseListDto;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomResponseUserDto;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoomMessage;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoomUsers;
import shop.iamhyunjun.tunatalk.repository.chat.ChatRoomMessageRepository;
import shop.iamhyunjun.tunatalk.repository.chat.ChatRoomRepository;
import shop.iamhyunjun.tunatalk.repository.chat.ChatRoomUsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMessageRepository chatRoomMessageRepository;
    private final ChatRoomUsersRepository chatRoomUsersRepository;
    @Transactional
    public ChatRoomResponseDto createChatRoom(String roomName) {
        ChatRoom chatRoom = new ChatRoom(roomName);
        chatRoomRepository.save(chatRoom);

        ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto(chatRoom);
        return chatRoomResponseDto;
    }

    public List<ChatRoomResponseDto> readChatRooms(UserDetailsImpl userDetailsImpl) {
        List<ChatRoomUsers> chatRoomUsers = chatRoomUsersRepository.findAllByRoomUser(userDetailsImpl.getUser());

        List<ChatRoom> chatRooms = new ArrayList<>();

        for (ChatRoomUsers chatRoomUser : chatRoomUsers) {
            chatRooms.add(chatRoomUser.getChatRoom());
        }

        List<ChatRoomResponseDto> chatRoomResponseDtos = new ArrayList<>();
        for (ChatRoom chatRoom : chatRooms) {
            ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto(chatRoom);
            chatRoomResponseDtos.add(chatRoomResponseDto);
        }
        return chatRoomResponseDtos;
    }

    public ChatRoomResponseListDto readChatRoom(Long chatRoomId, UserDetailsImpl userDetailsImpl) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.findByChatRoomId(chatRoomId);
        ChatRoomResponseUserDto chatRoomResponseUserDto = new ChatRoomResponseUserDto(chatRoom.get(), userDetailsImpl.getUser());

        List<ChatRoomMessage> chatRoomMessages = chatRoomMessageRepository.findAllByChatRoom(chatRoom.get());

        List<ChatRoomMessagesResponseDto> chatRoomMessagesResponseDtos = new ArrayList<>();
        for (ChatRoomMessage chatRoomMessage : chatRoomMessages) {
            ChatRoomMessagesResponseDto chatRoomMessagesResponseDto = new ChatRoomMessagesResponseDto(chatRoom.get(), chatRoomMessage); // + Sender
            chatRoomMessagesResponseDtos.add(chatRoomMessagesResponseDto);
        }

        ChatRoomResponseListDto chatRoomResponseListDto = new ChatRoomResponseListDto(chatRoomResponseUserDto, chatRoomMessagesResponseDtos);
        return chatRoomResponseListDto;
    }
}
