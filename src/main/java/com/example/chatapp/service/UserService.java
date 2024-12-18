package com.example.chatapp.service;

import com.example.chatapp.dto.Response;
import com.example.chatapp.entity.UserEntity;
import com.example.chatapp.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Response<?> findUserBySession(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return new Response<>("로그인 중이 아님", HttpStatus.UNAUTHORIZED);
        }
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            return new Response<>(userOpt.get(), HttpStatus.OK);
        } else {
            return new Response<>(userId + "번 유저아이디 찾을수 없음", HttpStatus.NOT_FOUND);
        }
    }

    public Response<Optional<UserEntity>> findById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new Response<>(user, HttpStatus.OK);
        } else {
            return new Response<>(Optional.empty(), HttpStatus.NOT_FOUND);
        }
    }

    public Response<List<UserEntity>> findAllUsers() {
        Iterable<UserEntity> userIterable = userRepository.findAll();
        List<UserEntity> users = StreamSupport.stream(userIterable.spliterator(), false)
                .collect(Collectors.toList());
        return new Response<>(users, HttpStatus.OK);
    }

    public UserEntity findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public UserEntity registerUser(String username, String studentNumber, String password, List<Integer> subCode) {
        String role;
        if (studentNumber.length() == 6) {
            role = "Professor";
        } else if (studentNumber.length() == 8) {
            role = "Student";
        } else {
            throw new IllegalArgumentException("Invalid student number length");
        }

        UserEntity user = new UserEntity(username, studentNumber, password, subCode);
        user.setRole(role);

        // 사용자 저장 로직 추가
        userRepository.save(user);

        return user;
    }
}