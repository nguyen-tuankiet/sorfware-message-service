package sorfware.example.sorfware.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sorfware.example.sorfware.model.entity.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

}