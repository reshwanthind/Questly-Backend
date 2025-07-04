package com.example.questlybackend.controllers;

import com.example.questlybackend.dtos.QuestionDto;
import com.example.questlybackend.models.Question;
import com.example.questlybackend.models.QuestionDocument;
import com.example.questlybackend.services.QuestionSearchService;
import com.example.questlybackend.services.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionSearchService questionSearchService;
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService, QuestionSearchService questionSearchService) {
        this.questionService = questionService;
        this.questionSearchService = questionSearchService;
    }

    @GetMapping
    public List<Question> getAllQuestions(@RequestParam int page, @RequestParam int size) {
        return questionService.getQuestions(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionService.getQuestionById(id);
        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Question createQuestion(@RequestBody QuestionDto questionDto) {
        return questionService.createQuestion(questionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<QuestionDocument> searchQuestions(@RequestParam String query) {
        return questionSearchService.searchQuestions(query);
    }
}