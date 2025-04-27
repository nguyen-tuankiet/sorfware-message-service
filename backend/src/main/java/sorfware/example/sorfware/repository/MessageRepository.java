package sorfware.example.sorfware.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sorfware.example.sorfware.model.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    // Đã bỏ hàm findAllByOrderByTimestampAsc để tránh lỗi
}