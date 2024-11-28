package com.example.chatapp.api;

import com.example.chatapp.dto.Response;
import com.example.chatapp.dto.SignUpDto;
import com.example.chatapp.dto.SignInDto;
import com.example.chatapp.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Autowired
    private AuthService authService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<Response<String>> signUpUser(@RequestBody SignUpDto signupDto) {
        return authService.signup(signupDto).toResponseEntity();
    }

    //로그인
    @PostMapping("/signin")
    public ResponseEntity<Response<String>> signInUser(@RequestBody SignInDto signinDto, HttpServletRequest request) {
        return authService.signin(signinDto, request).toResponseEntity();
    }

    //로그아웃
    @GetMapping("/logout")
    public ResponseEntity<Response<String>> logOutUser(HttpServletRequest request) {
        return authService.logout(request).toResponseEntity();
    }

}