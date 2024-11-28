package com.example.chatapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chat_rooms")
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(nullable = false, unique = true)
    private int subCode; // 과목 코드

    @Column(nullable = false)
    private String roomName; // 채팅방 이름

    @Column(nullable = false)
    private Long creatorId; // 채팅방 생성자 ID
}
