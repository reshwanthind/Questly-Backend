package com.example.questlybackend.dtos;
import lombok.Data;

import java.util.Set;

@Data
public class AnswerDto {
    private Long id;
    private String content;
    private Long questionId;
    private Long userId;
}