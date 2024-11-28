package com.example.chatapp.service;

import com.example.chatapp.entity.ChatRoomEntity;
import com.example.chatapp.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    /**
     * 채팅방 생성
     */
    public ChatRoomEntity createChatRoom(String roomName, int subCode, Long creatorId) {
        ChatRoomEntity chatRoom = ChatRoomEntity.builder()
                .roomName(roomName)
                .subCode(subCode)
                .creatorId(creatorId)
                .build();
        return chatRoomRepository.save(chatRoom);
    }

    /**
     * 채팅방 입장 시 과목 코드 검증
     */
    public boolean validateSubCode(Long chatRoomId, int inputSubCode) {
        Optional<ChatRoomEntity> chatRoom = chatRoomRepository.findById(chatRoomId);
        if (chatRoom.isPresent()) {
            return chatRoom.get().getSubCode() == inputSubCode; // 입력한 코드와 채팅방 코드 비교
        }
        throw new RuntimeException("채팅방이 존재하지 않습니다.");
    }

    /**
     * 모든 채팅방 조회
     */
    public List<ChatRoomEntity> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    /**
     * 특정 채팅방 정보 조회
     */
    public ChatRoomEntity getChatRoomById(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("채팅방이 존재하지 않습니다."));
    }
}
