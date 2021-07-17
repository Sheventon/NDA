package ru.itis.chatsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.chatsservice.dto.UsersDto;
import ru.itis.chatsservice.models.ChatRoom;
import ru.itis.chatsservice.models.Message;
import ru.itis.chatsservice.models.User;
import ru.itis.chatsservice.repositories.ChatRoomRepository;
import ru.itis.chatsservice.repositories.MessageRepository;

import java.util.List;

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
        String requestUrlGetSender = URL_GET_USER + Long.valueOf(sender) + "/get";
        String requestUrlGetRecipient = URL_GET_USER + Long.valueOf(recipient) + "/get";
        User userSender = restTemplate.getForObject(requestUrlGetSender, User.class);
        User userRecipient = restTemplate.getForObject(requestUrlGetRecipient, User.class);

        ChatRoom chatRoom = null;

        if (userSender != null && userRecipient != null) {

            chatRoom = chatRoomRepository.findChatRoomByFirstUserIdAndAndSecondUserId(userSender.getId(), userRecipient.getId());

            if (chatRoom == null) {
                ChatRoom newChatRoom = new ChatRoom();
                newChatRoom.setFirstUserId(userSender.getId());
                newChatRoom.setSecondUserId(userRecipient.getId());
                return chatRoomRepository.save(newChatRoom);
            }
        }
        return chatRoom;
    }

    @Override
    public User getUserById(Long id) {
        String requestUrlGetUser = URL_GET_USER + id + "/get";
        return restTemplate.getForObject(requestUrlGetUser, User.class);
    }

    @Override
    public UsersDto getInfoAboutUsers(Long recipientId) {
        String requestUrlGetRecipient = URL_GET_USER + recipientId + "/get";
        //TODO добавить получение id аутентифицированного пользователя
        String requestUrlGetSender = URL_GET_USER + 1 + "/get";
        User userRecipient = restTemplate.getForObject(requestUrlGetRecipient, User.class);
        User userSender = restTemplate.getForObject(requestUrlGetSender, User.class);
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