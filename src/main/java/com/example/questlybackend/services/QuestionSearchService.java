package com.example.questlybackend.services;

import com.example.questlybackend.models.QuestionDocument;
import com.example.questlybackend.repositories.QuestionDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionSearchService {
    private final QuestionDocumentRepository questionDocumentRepository;
    public QuestionSearchService(QuestionDocumentRepository questionDocumentRepository) {
        this.questionDocumentRepository = questionDocumentRepository;
    }
    public List<QuestionDocument> searchQuestions(String query) {
        return questionDocumentRepository.findByTitleContainingOrContentContaining(query, query);
    }
}
