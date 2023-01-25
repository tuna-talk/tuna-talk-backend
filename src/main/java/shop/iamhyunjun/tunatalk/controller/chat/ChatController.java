package shop.iamhyunjun.tunatalk.controller.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.iamhyunjun.tunatalk.dto.chat.ChatMessageDto;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final SimpMessageSendingOperations messagingTemplate;
    @ResponseBody
    @MessageMapping("/chat/message") // socket 통신은 request를 안주나???
    public void message(ChatMessageDto chatMessageDto) {
        messagingTemplate.convertAndSend("/sub/chat/room/" + chatMessageDto.getRoomId(), chatMessageDto);
    }
}
