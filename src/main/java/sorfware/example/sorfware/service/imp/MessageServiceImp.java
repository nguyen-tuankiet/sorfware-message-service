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

    /*
        Usecase 2: Nhắn tin
        Flow 2.1, 2.3, 2.6 bên FE - MainChat.jsx
   */
    public Message save(Message message) {
        // FLOW 2.2: Tạo phòng chat (nếu cần): Gọi getRoomId để kiểm tra phòng chat đã tôn tại hay chua
        var chatId = roomService.getRoomId(message.getSenderId(), message.getRecipientId(), true)
                .orElseThrow(() -> new RuntimeException("Cannot create chatId"));
        message.setChatId(chatId);
        //Lưu tin nhắn vào database
        messageRepository.save(message);
        //Cập nhật tin nhắn cuối trong phòng chat
        roomService.updateLastMessage(chatId, message);
        return message;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }


    @Override
    public List<Message> findMessageByKeyword(String keyword) {
//4.6.2 Truy vấn tin nhắn từ Database
//4.6.3 Database trả kết quả cho Service
        return messageRepository.findByContentContainingIgnoreCase(keyword);
    }

    //Phương thức lấy tin nhắn giữa 2 người
    @Override
    public List<Message> getMessageHistory(String chatId) {
        return messageRepository.findByChatIdOrderByTimestampAsc(chatId);
    }

}
