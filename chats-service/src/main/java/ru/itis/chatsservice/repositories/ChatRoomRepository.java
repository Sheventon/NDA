package ru.itis.chatsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.chatsservice.models.ChatRoom;
import ru.itis.chatsservice.models.User;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("select c from ChatRoom c where (c.FirstUserId = ?1 and c.SecondUserId = ?2) or (c.FirstUserId = ?2 and c.SecondUserId = ?1)")
    ChatRoom findChatRoomByFirstUserIdAndAndSecondUserId(Long firstUserId, Long secondUserId);
}
