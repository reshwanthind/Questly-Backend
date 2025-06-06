package com.example.questlybackend.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "questions")
public class QuestionDocument {
    @Id
    private String id;
    private String content;
    private String title;
    private String userId;
}
