package sorfware.example.sorfware.service;

import jakarta.servlet.http.HttpSession;
import sorfware.example.sorfware.model.dto.request.LoginRequest;
import sorfware.example.sorfware.model.dto.response.LoginResponse;

import java.util.Optional;

public interface AuthService {
    Optional<LoginResponse> login(LoginRequest loginRequest, HttpSession session);
    Optional<String> getCurrentUserId(HttpSession session);
}
