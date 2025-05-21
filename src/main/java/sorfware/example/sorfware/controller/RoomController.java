package sorfware.example.sorfware.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sorfware.example.sorfware.model.entity.Room;
import sorfware.example.sorfware.service.RoomService;

import java.util.List;

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
}