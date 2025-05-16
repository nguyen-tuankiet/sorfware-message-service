package sorfware.example.sorfware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sorfware.example.sorfware.model.dto.response.ApiResponse;
import sorfware.example.sorfware.service.UserService;
import sorfware.example.sorfware.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<ApiResponse> search(@RequestBody SearchRequest request) {
        String keyword = request.getKeyword();

        // Gọi service tìm người dùng theo keyword
        List<?> userList = userService.findUserByKeyword(keyword);

        // Gọi service tìm tin nhắn theo keyword
        List<?> messageList = messageService.findMessageByKeyword(keyword);

        // Tổng hợp kết quả tìm kiếm
        SearchResult combinedResult = new SearchResult(userList, messageList);

        ApiResponse response = ApiResponse.builder(combinedResult)
                .message("Search success")
                .build();
//        ApiResponse response = new ApiResponse();
        response.setStatus(200);
        response.setResultCode(0);
        response.setMessage("Search success");
        response.setData(combinedResult);

        return ResponseEntity.ok(response);
    }

    // DTO nhận request từ client
    public static class SearchRequest {
        private String keyword;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }

    // DTO trả về kết quả tìm kiếm
    public static class SearchResult {
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
}
