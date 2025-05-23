package sorfware.example.sorfware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import sorfware.example.sorfware.model.dto.request.LoginRequest;
import sorfware.example.sorfware.service.AuthService;
import sorfware.example.sorfware.model.dto.response.ApiResponse;

import java.util.Collections;
import java.util.Optional;

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

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUserId(HttpSession session) {
        Optional<String> userId = authService.getCurrentUserId(session);
        if (userId.isPresent()) {
            return ResponseEntity.ok(Collections.singletonMap("userId", userId.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
    }
}