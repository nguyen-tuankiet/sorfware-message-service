package sorfware.example.sorfware.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sorfware.example.sorfware.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);

    List<User> findByNameContainingIgnoreCase(String keyword);
}
