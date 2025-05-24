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

//4.5.1 Gọi hàm tìm kiếm người dùng theo từ khóa
//4.5.4 Service trả kết quả cho Controller
        List<?> userList = userService.findUserByKeyword(keyword);
//4.6.1 Gọi hàm tìm kiếm tin nhắn theo từ khóa
//4.6.4Service trả kết quả cho Controlle
        List<?> messageList = messageService.findMessageByKeyword(keyword);
//4.7 Tổng hợp kiểm tra kết quả + trả về.
        SearchResult combinedResult = new SearchResult(userList, messageList);

        ApiResponse response = ApiResponse.builder(combinedResult)
                .message("Search success")
                .build();

        response.setStatus(200);
        response.setResultCode(0);
        response.setMessage("Search success");
        response.setData(combinedResult);
//4.7 Tổng hợp kiểm tra kết quả + trả về.
        return ResponseEntity.ok(response);
    }



}
