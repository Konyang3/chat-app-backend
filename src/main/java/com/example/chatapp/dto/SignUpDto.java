package com.example.chatapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignUpDto {
    private String username;
    private String student_number;
    private String password;
    private String password_confirm;
    private List<Integer> sub_code;
}
