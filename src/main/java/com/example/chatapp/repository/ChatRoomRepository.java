package com.example.chatapp.repository;

import com.example.chatapp.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
    Optional<ChatRoomEntity> findBySubCode(int subCode); // 과목 코드로 채팅방 검색
}
