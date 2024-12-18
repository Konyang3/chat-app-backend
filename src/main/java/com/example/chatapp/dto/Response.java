package com.example.chatapp.dto;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
public class Response<T> {
    private int status;
    private T data;

    public Response(T data, HttpStatus status) {
        this.status = status.value();
        this.data = data;
    }

    public ResponseEntity<Response<T>> toResponseEntity() {
        return ResponseEntity.status(this.status).body(this);
    }
}