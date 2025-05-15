package sorfware.example.sorfware.service.imp;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sorfware.example.sorfware.model.dto.request.LoginRequest;
import sorfware.example.sorfware.model.dto.response.LoginResponse;
import sorfware.example.sorfware.model.entity.User;
import sorfware.example.sorfware.repository.UserRepository;
import sorfware.example.sorfware.service.AuthService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final UserRepository userRepository;

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
