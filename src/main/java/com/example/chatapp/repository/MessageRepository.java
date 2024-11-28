package com.example.chatapp.repository;

import com.example.chatapp.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findByChatRoomIdOrderByTimestampAsc(Long chatRoomId);
}

