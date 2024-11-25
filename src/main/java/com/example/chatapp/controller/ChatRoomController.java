package com.example.chatapp.controller;

import com.example.chatapp.entity.ChatRoom;
import com.example.chatapp.service.ChatRoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> getAllRooms() {
        return chatRoomService.getAllRooms();
    }
}
