package sorfware.example.sorfware.service;

import java.util.Optional;

public interface RoomService {
     String createChatId(String senderId, String recipientId);
     Optional<String> getRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExist);
}
