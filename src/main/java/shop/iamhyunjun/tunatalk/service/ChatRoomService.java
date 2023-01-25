package shop.iamhyunjun.tunatalk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.iamhyunjun.tunatalk.dto.ChatRoomMessagesResponseDto;
import shop.iamhyunjun.tunatalk.dto.ChatRoomResponseDto;
import shop.iamhyunjun.tunatalk.dto.ChatRoomResponseListDto;
import shop.iamhyunjun.tunatalk.entity.ChatRoom;
import shop.iamhyunjun.tunatalk.entity.ChatRoomMessage;
import shop.iamhyunjun.tunatalk.repository.ChatRoomMessageRepository;
import shop.iamhyunjun.tunatalk.repository.ChatRoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMessageRepository chatRoomMessageRepository;
    @Transactional
    public ChatRoomResponseDto createChatRoom(String roomName) {
        ChatRoom chatRoom = new ChatRoom(roomName);
        chatRoomRepository.save(chatRoom);

        ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto(chatRoom);
        return chatRoomResponseDto;
    }

    public List<ChatRoomResponseDto> readChatRooms() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        List<ChatRoomResponseDto> chatRoomResponseDtos = new ArrayList<>();
        for (ChatRoom chatRoom : chatRooms) {
            ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto(chatRoom);
            chatRoomResponseDtos.add(chatRoomResponseDto);
        }
        return chatRoomResponseDtos;
    }

    public ChatRoomResponseListDto readChatRoom(Long chatRoomId) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.findByChatRoomId(chatRoomId);
        ChatRoomResponseDto chatRoomResponseDto = new ChatRoomResponseDto(chatRoom.get());

        List<ChatRoomMessage> chatRoomMessages = chatRoomMessageRepository.findAllByChatRoom(chatRoom.get());

        List<ChatRoomMessagesResponseDto> chatRoomMessagesResponseDtos = new ArrayList<>();
        for (ChatRoomMessage chatRoomMessage : chatRoomMessages) {
            //chatRoomMessage.getSender()
            ChatRoomMessagesResponseDto chatRoomMessagesResponseDto = new ChatRoomMessagesResponseDto(chatRoom.get()); // + Sender
            chatRoomMessagesResponseDtos.add(chatRoomMessagesResponseDto);
        }

        ChatRoomResponseListDto chatRoomResponseListDto = new ChatRoomResponseListDto(chatRoomResponseDto, chatRoomMessagesResponseDtos);
        return chatRoomResponseListDto;
    }
}
