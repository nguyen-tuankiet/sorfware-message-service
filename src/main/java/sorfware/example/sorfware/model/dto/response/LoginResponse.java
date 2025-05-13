package sorfware.example.sorfware.model.dto.response;

import lombok.Builder;
import lombok.Data;
import sorfware.example.sorfware.model.entity.User;

@Data
@Builder
public class LoginResponse {
    private String id;
    private String email;
    private String name;
    // Thêm các trường khác cần thiết, nhưng KHÔNG có password
} 