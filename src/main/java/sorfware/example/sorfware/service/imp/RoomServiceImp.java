package sorfware.example.sorfware.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import sorfware.example.sorfware.model.entity.Message;
import sorfware.example.sorfware.model.entity.Room;
import sorfware.example.sorfware.repository.RoomRepository;
import sorfware.example.sorfware.service.RoomService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImp implements RoomService {
    private final RoomRepository roomRepository;
    private final MongoTemplate mongoTemplate;

    /**
     * UC3.2
     * service Lấy cuộc trò chuyện bằng id người gửi và id người nhận*
     * /*
       Usecase 2: Nhắn tin
       Flow 2.1, 2.3, 2.6 bên FE - MainChat.jsx
     */
    //  Flow 2.2: Tạo phòng chat (nếu cần)
    public Optional<String> getRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExist) {
       // 2.2.1 Kiểm tra phòng chat
        return roomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(Room::getChatId)
                .or(() -> {
                    // 2.2.2 Nếu phòng chat chưa tồn tại -> tạo phòng chat mới
                    if (createNewRoomIfNotExist) {
                        var chatId = createChatId(senderId, recipientId);
                        return Optional.of(chatId);
                    } else {
                        return Optional.empty();
                    }
                });
    }

    // 2.2.2 Nếu phòng chat chưa tồn tại -> tạo phòng chat mới
    public String createChatId(String senderId, String recipientId) {
        // Tạo ID phòng chat bằng cách kết hợp ID người gửi và người nhận
        var chatId = String.format("%s_%s", senderId, recipientId);
        // Tạo đối tượng Room mới
        Room senderRecipient = Room.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        Room recipientSender = Room.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();
        // Lưu phòng chat vào cơ sở dữ liệu
        roomRepository.save(senderRecipient);
        roomRepository.save(recipientSender);

        return chatId;
    }

    /**UC3:
     * Cập nhật tin nhắn cuối cùng trong phòng chat*/
    @Override
    public void updateLastMessage(String chatId, Message message) {
        List<Room> rooms = roomRepository.findAllByChatId(chatId);
        for (Room room : rooms) {
            room.setLastMessage(message);
            roomRepository.save(room);
        }
        System.out.println(roomRepository.findAllByChatId(chatId));
    }


    /**
     * UC3.1
     * service Hiển thị danh sách cuộc trò chuyện theo id user và sắp xếp theo thời gian tin nhắn cuối cùng*/
    @Override
    public List<Room> getRoomsByUserId(String userId) {
        return roomRepository.findRoomsByUserId(userId);
    }

    @Override
    public List<String> getRoomIdsByRecipientId(String recipientId) {
        List<Room> rooms = roomRepository.findAllByRecipientId(recipientId);
        return rooms.stream()
                .map(Room::getChatId)
                .distinct() // Loại bỏ các chatId trùng lặp
                .collect(Collectors.toList());
    }
}
