package shop.iamhyunjun.tunatalk.controller.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.iamhyunjun.tunatalk.dto.chat.ChatMessageDto;
import shop.iamhyunjun.tunatalk.dto.chat.ChatRoomMessagesResponseDto;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoom;
import shop.iamhyunjun.tunatalk.entity.chat.ChatRoomMessage;
import shop.iamhyunjun.tunatalk.entity.user.User;
import shop.iamhyunjun.tunatalk.repository.chat.ChatRoomMessageRepository;
import shop.iamhyunjun.tunatalk.repository.chat.ChatRoomRepository;
import shop.iamhyunjun.tunatalk.repository.user.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatRoomMessageRepository chatRoomMessageRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    @ResponseBody
    @MessageMapping("/chats") // socket 통신은 request를 안주나???
    public void message(ChatMessageDto chatMessageDto) {
        Optional<User> user = userRepository.findByUserName(chatMessageDto.getUserName());
        Optional<ChatRoom> chatRoom = chatRoomRepository.findByChatRoomId(chatMessageDto.getChatRoomId());
        ChatRoomMessage chatRoomMessage = new ChatRoomMessage(user.get(), chatMessageDto.getMessage(), chatRoom.get());
        chatRoomMessageRepository.save(chatRoomMessage);
        messagingTemplate.convertAndSend("/sub/chats/" + chatMessageDto.getChatRoomId(), chatMessageDto);
    }
}
