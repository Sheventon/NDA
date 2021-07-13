package ru.itis.chatsservice.services;

import ru.itis.chatsservice.dto.UsersDto;
import ru.itis.chatsservice.models.ChatRoom;
import ru.itis.chatsservice.models.Message;
import ru.itis.chatsservice.models.User;

import java.util.List;

public interface ChatService {

    public Message save(Message message);

    public List<Message> getMessages(String senderId, String receiverId);

    public ChatRoom getChatRoom(String sender, String recipient);

    User getUserById(Long id);

    UsersDto getInfoAboutUsers(Long recipientId);
}
