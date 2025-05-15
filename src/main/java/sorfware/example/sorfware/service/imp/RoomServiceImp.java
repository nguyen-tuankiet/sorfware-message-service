package sorfware.example.sorfware.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import sorfware.example.sorfware.model.entity.Room;
import sorfware.example.sorfware.repository.RoomRepository;
import sorfware.example.sorfware.service.RoomService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImp implements RoomService {
    private final RoomRepository roomRepository;
    private final MongoTemplate mongoTemplate;

    public Optional<String> getRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExist) {
        return roomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(Room::getChatId)
                .or(() -> {
                    if (createNewRoomIfNotExist) {
                        var chatId = createChatId(senderId, recipientId);
                        return Optional.of(chatId);
                    } else {
                        return Optional.empty();
                    }
                });
    }

    public String createChatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId);

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

        roomRepository.save(senderRecipient);
        roomRepository.save(recipientSender);

        return chatId;
    }
}
