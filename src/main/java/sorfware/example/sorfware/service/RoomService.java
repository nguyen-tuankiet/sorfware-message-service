package sorfware.example.sorfware.service;

import sorfware.example.sorfware.model.entity.Message;
import sorfware.example.sorfware.model.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
     String createChatId(String senderId, String recipientId);
     Optional<String> getRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExist);
     /**
      * UC3.1
      * service Hiển thị danh sách cuộc trò chuyện theo id user và sắp xếp theo thời gian tin nhắn cuối cùng*/
     List<Room> getRoomsByUserId(String userId);

     /**
      * Usecase 3.1 Lịch sửa trò chuyện
      * cập nhật tin nhắn cuối cùng để hiển thị trên danh sách các cuộc trò chuyện
      * tin nhắn cuối cùng sẽ được */
     public void updateLastMessage(String chatId, Message message);

}
