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

    @Override
    public List<Room> findRoomsByUserId(String userId) {
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("senderId").is(userId),
                Criteria.where("recipientId").is(userId)
        );
        Query query = new Query(criteria)
                .with(Sort.by(Sort.Direction.DESC, "lastMessage.timestamp"));

        return mongoTemplate.find(query, Room.class);
    }
}
