package ru.itis.chatsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.chatsservice.models.Message;

import javax.transaction.Transactional;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Transactional
    @Query("select m from Message m where (m.senderId= :senderId and m.recipientId = :receiverId) OR" +
            "(m.senderId= :receiverId and m.recipientId= :senderId)")
    public List<Message> getMessagesBySenderIdAndRecipientId(@Param("senderId") String senderId, @Param("receiverId") String receiverId);

}
