package com.example.questlybackend.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
}