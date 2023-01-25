package shop.iamhyunjun.tunatalk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import shop.iamhyunjun.tunatalk.dto.ChatRoomResponseDto;
import shop.iamhyunjun.tunatalk.dto.ChatRoomSearchRequestDto;
import shop.iamhyunjun.tunatalk.dto.ChatRoomSearchResponseDto;
import shop.iamhyunjun.tunatalk.service.ChatRoomSearchService;

@Controller // @ResponseBody 필요할때 쓰기!
@RequiredArgsConstructor
public class ChatRoomSearchController {
    private final ChatRoomSearchService chatRoomSearchService;
    @GetMapping("/chat/search")
    public ChatRoomSearchResponseDto chatSearch(@RequestBody ChatRoomSearchRequestDto chatRoomSearchRequestDto) {
        ChatRoomSearchResponseDto chatRoomSearchResponseDto = chatRoomSearchService.searchChatRoom(chatRoomSearchRequestDto);
        return chatRoomSearchResponseDto;
    }
}
