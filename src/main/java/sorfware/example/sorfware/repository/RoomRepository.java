package sorfware.example.sorfware.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sorfware.example.sorfware.model.entity.Room;

import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String>, RoomRepositoryCustom {
    /**
     * UC3.2
     * Lấy cuộc trò chuyện bằng id người gửi và id người nhận*/
    Optional<Room> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
