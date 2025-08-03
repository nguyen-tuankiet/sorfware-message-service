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
@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;

    private String userId;
    private String senderId;
    private String senderName;
    private String messageId;
    private String chatId;
    private String content;
    private String title;

    @JsonSerialize(using = DateSerializer.class)
    private Date timestamp;

    private boolean isRead;
    private String type;

    @JsonSerialize(using = DateSerializer.class)
    private Date readAt;
}