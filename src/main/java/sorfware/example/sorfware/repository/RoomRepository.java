package sorfware.example.sorfware.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sorfware.example.sorfware.model.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String>, RoomRepositoryCustom {

    // Usecase 2: Nhan tin
    // 2.2.1 Kiểm tra phòng chat
    Optional<Room> findBySenderIdAndRecipientId(String senderId, String recipientId);

    /**
     * UC3.2
     * Lấy cuộc trò chuyện bằng id người gửi và id người nhận*/
    List<Room> findAllByChatId(String chatId);
}
