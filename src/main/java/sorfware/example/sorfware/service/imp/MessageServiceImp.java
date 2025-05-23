package sorfware.example.sorfware.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import sorfware.example.sorfware.model.entity.Message;
import sorfware.example.sorfware.model.entity.Room;
import sorfware.example.sorfware.repository.MessageRepository;
import sorfware.example.sorfware.service.MessageService;
import sorfware.example.sorfware.service.RoomService;

import javax.management.Query;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImp implements MessageService {
    private final MessageRepository messageRepository;
    private final RoomService roomService;
    private final MongoTemplate mongoTemplate;

    public Message save(Message message) {
        var chatId = roomService.getRoomId(message.getSenderId(), message.getRecipientId(), true)
                .orElseThrow(() -> new RuntimeException("Cannot create chatId"));
        message.setChatId(chatId);
        messageRepository.save(message);

        //Cập nhật tin nhắn cuối trong phòng chat
        roomService.updateLastMessage(chatId, message);

        return message;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

 //4.2.5.2Truy vấn người dùng từ Database
    @Override
    public List<Message> findMessageByKeyword(String keyword) {
 //4.2.5.3Database trả kết quả cho Service
        return messageRepository.findByContentContainingIgnoreCase(keyword);
    }

    //Phương thức lấy tin nhắn giữa 2 người
    @Override
    public List<Message> getMessageHistory(String chatId) {
        return messageRepository.findByChatIdOrderByTimestampAsc(chatId);
    }

}
