package sorfware.example.sorfware.service;

import sorfware.example.sorfware.model.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
     String createChatId(String senderId, String recipientId);
     Optional<String> getRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExist);
     List<Room> getRoomsByUserId(String userId);
}
