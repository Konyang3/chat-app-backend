package com.example.chatapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String student_number;

    @Column(nullable = false)
    private String password;

    @ElementCollection
    @CollectionTable()
    @Column(name = "sub_code")
    private List<Integer> sub_code;

    public UserEntity(String username, String student_number, String password, List<Integer> sub_code) {
        this.username = username;
        this.student_number = student_number;
        this.password = password;
        this.sub_code = sub_code;
    }
}
