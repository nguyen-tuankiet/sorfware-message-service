package sorfware.example.sorfware.model.dto.response;

import java.util.List;

public class SearchResult {
    private List<?> users;
    private List<?> messages;

    public SearchResult(List<?> users, List<?> messages) {
        this.users = users;
        this.messages = messages;
    }

    public List<?> getUsers() {
        return users;
    }

    public void setUsers(List<?> users) {
        this.users = users;
    }

    public List<?> getMessages() {
        return messages;
    }

    public void setMessages(List<?> messages) {
        this.messages = messages;
    }
}
