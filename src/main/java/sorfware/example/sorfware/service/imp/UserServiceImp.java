package sorfware.example.sorfware.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sorfware.example.sorfware.model.entity.User;
import sorfware.example.sorfware.repository.UserRepository;
import sorfware.example.sorfware.service.UserService;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

//4.1.5.2Truy vấn người dùng từ Database
    @Override
    public List<User> findUserByKeyword(String keyword) {
//4.1.5.3Database trả kết quả cho Service
        return userRepository.findByNameContainingIgnoreCase(keyword);
    }

}
