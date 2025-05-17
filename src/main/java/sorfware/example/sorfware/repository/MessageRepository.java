package sorfware.example.sorfware.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import sorfware.example.sorfware.model.entity.Message;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findByContentContainingIgnoreCase(String keyword); // 4.6.3

    @Query("{ $or: [ { $and: [ { senderId: ?0 }, { recipientId: ?1 } ] }, { $and: [ { senderId: ?1 }, { recipientId: ?0 } ] } ] }")
    List<Message> findConversation(String senderId, String recipientId);

}