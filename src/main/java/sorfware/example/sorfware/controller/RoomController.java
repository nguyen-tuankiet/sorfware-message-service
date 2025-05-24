package sorfware.example.sorfware.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sorfware.example.sorfware.model.entity.Message;
import sorfware.example.sorfware.model.entity.Room;
import sorfware.example.sorfware.service.RoomService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    /**
     * UC3.2
     * controller Lấy cuộc trò chuyện bằng id người gửi và id người nhận*/
    @GetMapping("/{userId}")
    public ResponseEntity<List<Room>> getRoomsByUserId(@PathVariable String userId) {
        List<Room> rooms = roomService.getRoomsByUserId(userId);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/lastMessage/{chatId}")
    public ResponseEntity<Object> updateLastMessage(@PathVariable String chatId) {
        // Tạo một tin nhắn giả lập để test
        Message message = Message.builder()
                .id("test-msg-id")
                .chatId(chatId)
                .senderId("user1")
                .recipientId("user2")
                .content("Tin nhắn cuối cùng test")
                .timestamp(new Date())
                .build();

        // Gọi service để cập nhật lastMessage
        roomService.updateLastMessage(chatId, message);

        return ResponseEntity.ok("Đã cập nhật lastMessage cho chatId: " + chatId);
    }

    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<List<String>> getRoomIdByRecipientId(@PathVariable String recipientId) {
        List<String> roomIds = roomService.getRoomIdsByRecipientId(recipientId);
        return ResponseEntity.ok(roomIds);
    }
}
