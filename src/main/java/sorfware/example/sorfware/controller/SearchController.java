package sorfware.example.sorfware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sorfware.example.sorfware.model.dto.response.ApiResponse;
import sorfware.example.sorfware.service.UserService;
import sorfware.example.sorfware.service.MessageService;
import sorfware.example.sorfware.model.dto.request.SearchRequest;
import sorfware.example.sorfware.model.dto.response.SearchResult;


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


    // DTO trả về kết quả tìm kiếm

}
