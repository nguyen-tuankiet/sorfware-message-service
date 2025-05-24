package sorfware.example.sorfware.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sorfware.example.sorfware.model.entity.Room;

import java.util.List;

public interface RoomRepositoryCustom{
    /**
     * UC3.1
     * Hiển thị danh sách cuộc trò chuyện theo id user và sắp xếp theo thời gian tin nhắn cuối cùng*/
    List<Room> findRoomsByUserId(String userId);
}
