package sorfware.example.sorfware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import sorfware.example.sorfware.model.dto.request.LoginRequest;
import sorfware.example.sorfware.service.AuthService;
import sorfware.example.sorfware.model.dto.response.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        ApiResponse response = ApiResponse.builder(authService.login(loginRequest, session)).build();
        return ResponseEntity.ok(response);
    }
}