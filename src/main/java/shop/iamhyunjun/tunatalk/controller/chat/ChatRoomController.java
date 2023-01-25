package shop.iamhyunjun.tunatalk.controller.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.iamhyunjun.tunatalk.config.security.UserDetailsImpl;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomCreateRequestDto;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomResponseDto;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomResponseListDto;
import shop.iamhyunjun.tunatalk.service.chat.ChatRoomService;

import java.util.List;

@Controller // @ResponseBody 필요할때 쓰기!
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @ResponseBody
    @PostMapping("/chats")
    public ChatRoomResponseDto chatroomCreate(@RequestBody ChatRoomCreateRequestDto chatRoomCreateRequestDto) {
        ChatRoomResponseDto chatRoomResponseDto = chatRoomService.createChatRoom(chatRoomCreateRequestDto);
        return chatRoomResponseDto;
    }
    @ResponseBody
    @GetMapping("/chats")
    public List<ChatRoomResponseDto> chatRooms(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        System.out.println(userDetailsImpl.getUser().getUserNickname());
        List<ChatRoomResponseDto> chatRoomResponseDtos = chatRoomService.readChatRooms(userDetailsImpl);
        return chatRoomResponseDtos;
    }

    @ResponseBody
    @GetMapping("/chats/{chatRoomId}")
    public ChatRoomResponseListDto chatRoom(@PathVariable Long chatRoomId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        ChatRoomResponseListDto chatRoomResponseListDto = chatRoomService.readChatRoom(chatRoomId, userDetailsImpl);
        return chatRoomResponseListDto;
    }
}
