package sorfware.example.sorfware.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sorfware.example.sorfware.model.dto.response.ApiResponse;
import sorfware.example.sorfware.model.entity.User;
import sorfware.example.sorfware.repository.UserRepository;
import sorfware.example.sorfware.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        ApiResponse response = ApiResponse.builder(savedUser)
                .message("User created successfully")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ApiResponse getUserByid(@RequestParam("id") String id) {
        Optional<User> user = userService.findById(id);
        return ApiResponse.builder(user).build();
    }

}
