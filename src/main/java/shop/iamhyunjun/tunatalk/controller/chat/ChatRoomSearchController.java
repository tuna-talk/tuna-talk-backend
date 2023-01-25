package shop.iamhyunjun.tunatalk.controller.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import shop.iamhyunjun.tunatalk.config.security.UserDetailsImpl;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomSearchRequestDto;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomSearchResponseDto;
import shop.iamhyunjun.tunatalk.service.chat.ChatRoomSearchService;

import java.util.List;

@Controller // @ResponseBody 필요할때 쓰기!
@RequiredArgsConstructor
public class ChatRoomSearchController {
    private final ChatRoomSearchService chatRoomSearchService;
    @GetMapping("/chat/search")
    public List<ChatRoomSearchResponseDto> chatSearch(@RequestBody ChatRoomSearchRequestDto chatRoomSearchRequestDto,
                                                @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        List<ChatRoomSearchResponseDto> chatRoomSearchResponseDtos = chatRoomSearchService.searchChatRoom(chatRoomSearchRequestDto, userDetailsImpl);
        return chatRoomSearchResponseDtos;
    }
}
