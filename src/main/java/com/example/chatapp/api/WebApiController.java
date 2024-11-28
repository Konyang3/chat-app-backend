package com.example.chatapp.api;

import com.example.chatapp.dto.Response;
import com.example.chatapp.entity.UserEntity;
import com.example.chatapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class WebApiController {

    @Autowired
    private UserService userService;

    //id로 회원 정보
    @GetMapping("/{id}")
    public ResponseEntity<Response<Optional<UserEntity>>> getUserById(@PathVariable Long id) {
        return userService.findById(id).toResponseEntity();
    }

    //로그인중인 회원정보
    @GetMapping("/info")
    public ResponseEntity<? extends Response<?>> getUserInfo(HttpServletRequest request) {
        return userService.findUserBySession(request).toResponseEntity();
    }

    // 모든 회원정보
    @GetMapping("/users")
    public ResponseEntity<Response<List<UserEntity>>> getAllUsers() {
        return userService.findAllUsers().toResponseEntity();
    }
}