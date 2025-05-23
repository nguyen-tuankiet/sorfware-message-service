package sorfware.example.sorfware.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import sorfware.example.sorfware.model.entity.Message;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {


    List<Message> findByContentContainingIgnoreCase(String keyword); // 4.6.3

    /**
     * Usecase 3: Lịch sử trò chuyện
     * U3.1: Hiển thị tin nhắn cũ
     * query lấy ra danh sách tin nhắn bằng chatId*/
    List<Message> findByChatIdOrderByTimestampAsc(String chatId);
}

