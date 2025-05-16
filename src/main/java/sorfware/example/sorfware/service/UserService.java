package sorfware.example.sorfware.service;

import sorfware.example.sorfware.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(String  id);
    Optional<User> findByName(String name);

    List<?> findUserByKeyword(String keyword);
}
