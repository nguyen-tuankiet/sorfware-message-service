package sorfware.example.sorfware.repository.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import sorfware.example.sorfware.model.entity.Room;
import sorfware.example.sorfware.repository.RoomRepositoryCustom;

import java.util.List;

public class RoomRepositoryImpl implements RoomRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * UC3.1
     * Hiển thị danh sách cuộc trò chuyện theo id user và sắp xếp theo thời gian tin nhắn cuối cùng*/
    @Override
    public List<Room> findRoomsByUserId(String userId) {
        Criteria criteria = Criteria.where("senderId").is(userId); // Tìm phòng có senderId là userId
        Query query = new Query(criteria)
                .with(Sort.by(Sort.Direction.DESC, "lastMessage.timestamp")); // Sắp xếp theo thời gian tin nhắn cuối

        return mongoTemplate.find(query, Room.class);
    }

}
