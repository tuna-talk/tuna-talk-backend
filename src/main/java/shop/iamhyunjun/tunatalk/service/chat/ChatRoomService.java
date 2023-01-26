package shop.iamhyunjun.tunatalk.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.iamhyunjun.tunatalk.config.security.UserDetailsImpl;
import shop.iamhyunjun.tunatalk.dto.chat.*;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoomMessage;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoomUsers;
import shop.iamhyunjun.tunatalk.entity.user.User;
import shop.iamhyunjun.tunatalk.repository.chat.ChatRoomMessageRepository;
import shop.iamhyunjun.tunatalk.repository.chat.ChatRoomRepository;
import shop.iamhyunjun.tunatalk.repository.chat.ChatRoomUsersRepository;
import shop.iamhyunjun.tunatalk.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMessageRepository chatRoomMessageRepository;
    private final ChatRoomUsersRepository chatRoomUsersRepository;
    private final UserRepository userRepository;
    @Transactional
    public ChatRoomResponseDto createChatRoom(ChatRoomCreateRequestDto chatRoomCreateRequestDto, UserDetailsImpl userDetailsImpl) {
        Optional<User> user = userRepository.findByUserEmail(chatRoomCreateRequestDto.getOtherUserEmail());
        System.out.println(user.get().getUserName());

        if (user.isEmpty()) {
            throw new IllegalArgumentException("유저가 존재하지 않습니다.");
        }

        if (chatRoomRepository.findByUser1AndUser2(userDetailsImpl.getUser(), user.get()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 채팅방입니다.");
        }

        ChatRoom chatRoom = new ChatRoom(userDetailsImpl.getUser(), user.get());
        chatRoomRepository.save(chatRoom);

        ChatRoomUsers chatRoomUsers1 = new ChatRoomUsers(chatRoom, userDetailsImpl.getUser());
        chatRoomUsersRepository.save(chatRoomUsers1);

        ChatRoomUsers chatRoomUsers2 = new ChatRoomUsers(chatRoom, user.get());
        chatRoomUsersRepository.save(chatRoomUsers2);

        ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto(chatRoom, userDetailsImpl.getUser());
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
            ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto(chatRoom, userDetailsImpl.getUser());
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
