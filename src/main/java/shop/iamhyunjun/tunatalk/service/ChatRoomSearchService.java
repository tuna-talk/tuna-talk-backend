package shop.iamhyunjun.tunatalk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.iamhyunjun.tunatalk.dto.ChatRoomResponseDto;
import shop.iamhyunjun.tunatalk.dto.ChatRoomSearchRequestDto;
import shop.iamhyunjun.tunatalk.dto.ChatRoomSearchResponseDto;
import shop.iamhyunjun.tunatalk.entity.ChatRoom;
import shop.iamhyunjun.tunatalk.repository.ChatRoomRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomSearchService {
    private final ChatRoomRepository chatRoomRepository;
    public ChatRoomSearchResponseDto searchChatRoom(ChatRoomSearchRequestDto chatRoomSearchRequestDto) {
        //chatRoomRepository.findByChatRoomId()
        ChatRoomSearchResponseDto chatRoomSearchResponseDto = new ChatRoomSearchResponseDto();
        return chatRoomSearchResponseDto;
    }
}
