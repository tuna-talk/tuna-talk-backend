package shop.iamhyunjun.tunatalk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.iamhyunjun.tunatalk.dto.ChatRoomResponseDto;
import shop.iamhyunjun.tunatalk.dto.ChatRoomResponseListDto;
import shop.iamhyunjun.tunatalk.service.ChatRoomService;

import java.util.List;

@Controller // @ResponseBody 필요할때 쓰기!
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
//    @GetMapping("/chatroom")
//    public String chatroom(Model model) {
//        String senderId = chatRoomService.createSenderId();
//        model.addAttribute("senderId", senderId);
//        return "Chat";
//    }
//
//    @GetMapping("/chat/room/{roomUUID}")
//    public String chatRoom(Model model) {
//        return "ChatRoom";
//    }
//
//    @ResponseBody
//    @PostMapping("/chat/room/enter/{roomUUID}")
//    public List<ChatRoomMessageResponseDto> chatRoomEnter(String roomUUID,
//                         @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//        List<ChatRoomMessageResponseDto> chatRoomMessageResponseDtos = chatRoomService.enterChatRoom(roomUUID, userDetailsImpl);
//        return chatRoomMessageResponseDtos;
//    }
//
//    @ResponseBody
//    @PostMapping("/chat/room/exit/{roomUUID}")
//    public void chatRoomExit(String roomUUID,
//                              @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//        chatRoomService.exitChatRoom(roomUUID, userDetailsImpl);
//    }
//
//    @ResponseBody
//    @GetMapping("/chatrooms-list")
//    public List<ChatRoomResponseDto> chatRoomsList() {
//        List<ChatRoomResponseDto> chatRoomResponseDtos = chatRoomService.readChatRooms();
//        return chatRoomResponseDtos;
//    }




    @ResponseBody
    @PostMapping("/chat")
    public ChatRoomResponseDto chatroomCreate(String roomName) {
        ChatRoomResponseDto chatRoomResponseDto = chatRoomService.createChatRoom(roomName);
        return chatRoomResponseDto;
    }

    @GetMapping("/chats")
    public List<ChatRoomResponseDto> chatRooms() {
        List<ChatRoomResponseDto> chatRoomResponseDtos = chatRoomService.readChatRooms();
        return chatRoomResponseDtos;
    }

    @GetMapping("/chat/{chatRoomId}")
    public ChatRoomResponseListDto chatRoom(@PathVariable Long chatRoomId) {
        ChatRoomResponseListDto chatRoomResponseListDto = chatRoomService.readChatRoom(chatRoomId);
        return chatRoomResponseListDto;
    }
}
