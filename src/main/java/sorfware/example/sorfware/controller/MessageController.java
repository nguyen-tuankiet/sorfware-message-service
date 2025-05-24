package sorfware.example.sorfware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sorfware.example.sorfware.model.entity.ChatNotification;
import sorfware.example.sorfware.model.entity.Message;
import sorfware.example.sorfware.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /*
    Usecase 2: Nhắn tin
    Flow 2.1, 2.3, 2.6 bên FE - MainChat.jsx
     */
    @MessageMapping("/chat")
    public void processMessage(@Payload Message message) {
        System.out.println("Message: " + message);
        // FLOW 2.4.1: Lưu tin nhắn vào DB thông qua MessageService
        Message savedMessage = messageService.save(message);
        // FLOW 2.5.1: Tạo ChatNotification từ Message đã lưu và Gửi thông báo đến người nhận thông qua WebSocket
        simpMessagingTemplate.convertAndSendToUser(             // Gửi thông báo đến người nhận thông qua WebSocket
                message.getRecipientId(),                      // Người nhận
                "/queue/messages",                            // Kênh gửi đến
                ChatNotification.builder()                // Tạo ChatNotification từ Message đã lưu
                        .id(savedMessage.getId())
                        .senderId(savedMessage.getSenderId())
                        .recipientId(savedMessage.getRecipientId())
                        .content(savedMessage.getContent())
                        .timestamp(savedMessage.getTimestamp())
                        .build()
        );

        // Gửi sự kiện cập nhật room cho cả 2 người dùng (sender & recipient)
        simpMessagingTemplate.convertAndSendToUser(
                message.getSenderId(),
                "/queue/rooms",
                savedMessage);

        simpMessagingTemplate.convertAndSendToUser(
                message.getRecipientId(),
                "/queue/rooms",
                savedMessage);
    }

    // REST API để lấy danh sách tin nhắn (dùng cho load lịch sử chat)
    @GetMapping("/messages")
    @ResponseBody
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }

    /**
     * Usecase 3: Lịch sử trò chuyện
     * U3.2: Hiển thị tin nhắn cũ
     */
    @GetMapping("/history")
    @ResponseBody
    public List<Message> getMessageHistory(@RequestParam String chatId) {
        return messageService.getMessageHistory(chatId);
    }
}
