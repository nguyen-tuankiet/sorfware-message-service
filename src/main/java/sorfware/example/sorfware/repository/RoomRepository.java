package sorfware.example.sorfware.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sorfware.example.sorfware.model.entity.Room;

import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String> {
    Optional<Room> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
