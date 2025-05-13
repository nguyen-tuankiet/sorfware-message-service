package sorfware.example.sorfware.service.imp;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import sorfware.example.sorfware.model.dto.request.LoginRequest;
import sorfware.example.sorfware.model.dto.response.LoginResponse;
import sorfware.example.sorfware.model.entity.User;
import sorfware.example.sorfware.repository.UserRepository;
import sorfware.example.sorfware.service.AuthService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AuthServiceImp implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public  Optional<LoginResponse> login(LoginRequest loginRequest, HttpSession session) {
        return userRepository.findByEmail(loginRequest.getEmail())
                .filter(user -> user.getPassword() != null && user.getPassword().equals(loginRequest.getPassword()))
                .map(user -> {
                    session.setAttribute("userId", user.getId());
                    return convertToLoginResponse(user);
                });
    }

    @Override
    public Optional<String> getCurrentUserId(HttpSession session) {
        Object userId = session.getAttribute("userId");
        return Optional.ofNullable(userId).map(id -> (String) id);
    }

    private LoginResponse convertToLoginResponse(User user) {
        return LoginResponse.builder()
                .id((user.getId()))
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
