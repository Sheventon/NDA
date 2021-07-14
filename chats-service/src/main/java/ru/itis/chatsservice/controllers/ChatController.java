package ru.itis.chatsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ru.itis.chatsservice.dto.UsersDto;
import ru.itis.chatsservice.models.ChatRoom;
import ru.itis.chatsservice.models.Message;
import ru.itis.chatsservice.models.User;
import ru.itis.chatsservice.services.ChatService;

@RestController
public class ChatController {

    @Autowired
    ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void chat(@Payload Message message) {
        ChatRoom chat = chatService.getChatRoom(message.getSenderId(), message.getRecipientId());
        message.setChatRoom(chat);
        message = chatService.save(message);

        messagingTemplate.convertAndSendToUser(message.getRecipientId(),
                "queue/messages", message);
    }

    @GetMapping("/chat/{id}")
    public ResponseEntity<UsersDto> getUsersInfo(@PathVariable("id") Long id){
        return ResponseEntity.ok(chatService.getInfoAboutUsers(id));
    }

    @GetMapping("/users/me")
    public ResponseEntity<?> getAuthUser() {
        //TODO опять же таки решить момент как брать id аутентифицированного пользователя
        User user = chatService.getUserById(1L);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/messages/{receptorId}")
    public ResponseEntity<?> getAllMessages(@PathVariable("receptorId") String receptorId) {
        //TODO аналогично как и в методе getAuthUser
        User sender = chatService.getUserById(1L);
        return ResponseEntity.ok(chatService.getMessages(String.valueOf(sender.getId()), receptorId));
    }

}
