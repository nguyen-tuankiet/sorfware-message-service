package sorfware.example.sorfware.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("rooms")
public class Room {
    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
    @JsonSerialize(using = DateSerializer.class)
    private Date timestamp;
    private Message lastMessage;
}
