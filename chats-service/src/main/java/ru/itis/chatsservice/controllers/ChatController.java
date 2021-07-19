package ru.itis.chatsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itis.chatsservice.dto.UsersDto;
import ru.itis.chatsservice.models.ChatRoom;
import ru.itis.chatsservice.models.Message;
import ru.itis.chatsservice.models.User;
import ru.itis.chatsservice.services.ChatService;
import ru.itis.security.details.UserDetailsImpl;

@RestController
@RequestMapping(value = "/messenger")
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

    @PostMapping("/chat/{id}")
    public ResponseEntity<UsersDto> getUsersInfo(@PathVariable("id") Long recipientId,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(chatService.getInfoAboutUsers(recipientId, userDetails.getId(), userDetails.getToken()));
    }

    @PostMapping("/users/me")
    public ResponseEntity<?> getAuthUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = chatService.getUserById(userDetails.getId(), userDetails.getToken());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/messages/{receptorId}")
    public ResponseEntity<?> getAllMessages(@PathVariable("receptorId") String receptorId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User sender = chatService.getUserById(userDetails.getId(), userDetails.getToken());
        return ResponseEntity.ok(chatService.getMessages(String.valueOf(sender.getId()), receptorId));
    }

}
