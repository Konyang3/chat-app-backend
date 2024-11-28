package com.example.chatapp.controller;

import com.example.chatapp.entity.ChatRoomEntity;
import com.example.chatapp.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {
    @Autowired
    private ChatRoomService chatRoomService;

    /**
     * 채팅방 생성
     */
    @PostMapping("/create")
    public ResponseEntity<ChatRoomEntity> createChatRoom(
            @RequestParam String roomName,
            @RequestParam int subCode,
            @RequestParam Long creatorId) {
        ChatRoomEntity chatRoom = chatRoomService.createChatRoom(roomName, subCode, creatorId);
        return new ResponseEntity<>(chatRoom, HttpStatus.CREATED);
    }

    /**
     * 채팅방 입장 검증
     */
    @GetMapping("/validate")
    public ResponseEntity<String> validateSubCode(
            @RequestParam Long chatRoomId,
            @RequestParam int subCode) {
        boolean isValid = chatRoomService.validateSubCode(chatRoomId, subCode);
        if (isValid) {
            return new ResponseEntity<>("입장 성공: 코드가 유효합니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("입장 실패: 코드가 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 모든 채팅방 조회
     */
    @GetMapping
    public ResponseEntity<List<ChatRoomEntity>> getAllChatRooms() {
        List<ChatRoomEntity> chatRooms = chatRoomService.getAllChatRooms();
        return new ResponseEntity<>(chatRooms, HttpStatus.OK);
    }
}
