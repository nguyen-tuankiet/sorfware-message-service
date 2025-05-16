package sorfware.example.sorfware.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sorfware.example.sorfware.model.entity.Message;
import sorfware.example.sorfware.model.entity.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResult {
    private List<User> users;
    private List<Message> messages;
}
