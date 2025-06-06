package com.example.questlybackend.services;

import com.example.questlybackend.models.Question;
import com.example.questlybackend.models.QuestionDocument;
import com.example.questlybackend.repositories.QuestionDocumentRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionIndexService {

    private final QuestionDocumentRepository questionDocumentRepository;

    public QuestionIndexService(QuestionDocumentRepository questionDocumentRepository) {
        this.questionDocumentRepository = questionDocumentRepository;
    }
    public void indexQuestion(Question question) {

        QuestionDocument questionDocument = new QuestionDocument();
        questionDocument.setId(question.getId().toString());
        questionDocument.setTitle(question.getTitle());
        questionDocument.setContent(question.getContent());
        questionDocument.setUserId(question.getUser().getId().toString());

        questionDocumentRepository.save(questionDocument);
    }
}
