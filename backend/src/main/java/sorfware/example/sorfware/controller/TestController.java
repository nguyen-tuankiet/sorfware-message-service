package sorfware.example.sorfware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/mongo")
    public ResponseEntity<String> testMongoConnection() {
        try {
            // Try to get database info
            String dbName = mongoTemplate.getDb().getName();
            return ResponseEntity.ok("Successfully connected to MongoDB! Database: " + dbName);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to connect to MongoDB: " + e.getMessage());
        }
    }
} 