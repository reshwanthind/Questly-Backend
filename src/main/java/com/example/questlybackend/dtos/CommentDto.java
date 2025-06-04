package com.example.questlybackend.dtos;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String content;
    private Long answerId;
    private Long parentCommentId;
}