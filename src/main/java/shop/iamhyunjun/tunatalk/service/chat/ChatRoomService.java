package shop.iamhyunjun.tunatalk.service.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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
        Optional<User> user = userRepository.findByUserEmail(chatRoomCreateRequestDto.getFriendEmail());

        if (user.isEmpty()) {
            throw new IllegalArgumentException("유저가 존재하지 않습니다.");
        }

        if (chatRoomRepository.findByUser1AndUser2(userDetailsImpl.getUser(), user.get()).isPresent()
                || chatRoomRepository.findByUser1AndUser2(user.get(), userDetailsImpl.getUser()).isPresent()) {
            System.out.println("새로운 테스트");
            Optional<ChatRoom> chatRoom1 = chatRoomRepository.findByUser1AndUser2(userDetailsImpl.getUser(), user.get());
            Optional<ChatRoom> chatRoom2 = chatRoomRepository.findByUser1AndUser2(user.get(), userDetailsImpl.getUser());
            if (chatRoom1.isEmpty()) {
                ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto(chatRoom2.get(), userDetailsImpl.getUser());
                return chatRoomResponseDto;
            } else {
                ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto(chatRoom1.get(), userDetailsImpl.getUser());
                return chatRoomResponseDto;
            }
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
            if (chatRoom.getUser1() != null && chatRoom.getUser2() != null) {
                ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto(chatRoom, userDetailsImpl.getUser());
                chatRoomResponseDtos.add(chatRoomResponseDto);
            }
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

    @Transactional
    public ChatRoomDeleteResponseDto deleteChatRoom(@PathVariable Long chatRoomId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.findByChatRoomId(chatRoomId);

        if (chatRoom.get().getUser1().getUserEmail().equals(userDetailsImpl.getUser().getUserEmail())) {
            chatRoom.get().ChatRoomUser1Delete();
            chatRoomRepository.save(chatRoom.get());
        } else {
            chatRoom.get().ChatRoomUser2Delete();
            chatRoomRepository.save(chatRoom.get());
        }

        Optional<ChatRoomUsers> chatRoomUsers = chatRoomUsersRepository.findByChatRoomAndRoomUser(chatRoom.get(), userDetailsImpl.getUser());
        chatRoomUsersRepository.delete(chatRoomUsers.get());

        ChatRoomDeleteResponseDto chatRoomDeleteResponseDto = new ChatRoomDeleteResponseDto();

        return chatRoomDeleteResponseDto;
    }
}
