package ru.itis.chatsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.chatsservice.dto.UsersDto;
import ru.itis.chatsservice.models.ChatRoom;
import ru.itis.chatsservice.models.Message;
import ru.itis.chatsservice.models.User;
import ru.itis.chatsservice.repositories.ChatRoomRepository;
import ru.itis.chatsservice.repositories.MessageRepository;
import ru.itis.security.details.UserDetailsImpl;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
public class ChatServiceImpl implements ChatService {

    private final String URL_GET_USER = "http://user-service/users/";

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessages(String senderId, String receiverId) {
        return messageRepository.getMessagesBySenderIdAndRecipientId(senderId, receiverId);
    }

    @Override
    public ChatRoom getChatRoom(String sender, String recipient) {

        ChatRoom chatRoom = null;

        Long userSender = Long.valueOf(sender);
        Long userRecipient = Long.valueOf(recipient);

        chatRoom = chatRoomRepository.findChatRoomByFirstUserIdAndAndSecondUserId(userSender, userRecipient);

        if (chatRoom == null) {
            ChatRoom newChatRoom = new ChatRoom();
            newChatRoom.setFirstUserId(userSender);
            newChatRoom.setSecondUserId(userRecipient);
            return chatRoomRepository.save(newChatRoom);
        }
        return chatRoom;
    }

    @Override
    public User getUserById(Long id, String token) {
        String requestUrlGetUser = URL_GET_USER + id + "/get";

        RequestEntity<?> reqUser = RequestEntity.get(requestUrlGetUser)
                .header(AUTHORIZATION, token)
                .build();
        ResponseEntity<User> resUser = restTemplate.exchange(reqUser, User.class);
        return resUser.getBody();
    }

    @Override
    public UsersDto getInfoAboutUsers(Long recipientId, Long senderId, String token) {

        String requestUrlGetRecipient = URL_GET_USER + recipientId + "/get";
        String requestUrlGetSender = URL_GET_USER + senderId + "/get";

        RequestEntity<?> requestSender = RequestEntity.get(requestUrlGetSender)
                .header(AUTHORIZATION, token)
                .build();
        RequestEntity<?> requestRecipient = RequestEntity.get(requestUrlGetRecipient)
                .header(AUTHORIZATION, token)
                .build();

        ResponseEntity<User> resSender = restTemplate.exchange(requestSender, User.class);
        ResponseEntity<User> resRecipient = restTemplate.exchange(requestRecipient, User.class);

        User userSender = resSender.getBody();
        User userRecipient = resRecipient.getBody();

        if (userSender != null && userRecipient != null) {
            return UsersDto.builder()
                    .senderId(userSender.getId())
                    .senderFirstName(userSender.getFirstName())
                    .senderLastName(userSender.getLastName())
                    .recipientId(userRecipient.getId())
                    .recipientFirstName(userRecipient.getFirstName())
                    .recipientLastName(userRecipient.getLastName())
                    .build();
        }
        return UsersDto.builder().build();
    }

    static class Config {
        private String url;
    }

}
