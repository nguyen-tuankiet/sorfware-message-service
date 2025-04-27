package sorfware.example.sorfware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sorfware.example.sorfware.model.Message;
import sorfware.example.sorfware.service.ChatService;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message) {
        System.out.println("Đã nhận message: " + message);
        return chatService.saveMessage(message);
    }

    // REST API để lấy danh sách tin nhắn (dùng cho load lịch sử chat)
    @GetMapping("/messages")
    @ResponseBody
    public List<Message> getMessages() {
        return chatService.getAllMessages();
    }
}
