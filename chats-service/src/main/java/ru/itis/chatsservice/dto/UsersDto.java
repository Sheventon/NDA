package ru.itis.chatsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDto {

    private Long senderId;

    private String senderFirstName;

    private String senderLastName;

    private Long recipientId;

    private String recipientFirstName;

    private String recipientLastName;
}
