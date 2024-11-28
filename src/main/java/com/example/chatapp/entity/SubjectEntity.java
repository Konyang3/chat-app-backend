package com.example.chatapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subjects")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subID;

    @Column(nullable = false, unique = true)
    private String professor;

    @Column(nullable = false, unique = true)
    private String classroom;

    @Column(nullable = false, unique = true)
    private String sub_name;

    @Column(nullable = false)
    private int sub_code;

    public SubjectEntity(String professor, String classroom, String sub_name, int sub_code) {
        this.professor = professor;
        this.classroom = classroom;
        this.sub_name = sub_name;
        this.sub_code = sub_code;
    }
}