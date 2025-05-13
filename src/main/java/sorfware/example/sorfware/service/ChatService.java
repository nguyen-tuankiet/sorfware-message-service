package sorfware.example.sorfware.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sorfware.example.sorfware.model.entity.Message;
import sorfware.example.sorfware.repository.MessageRepository;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        System.out.println("Đã nhận message: " + message);
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
