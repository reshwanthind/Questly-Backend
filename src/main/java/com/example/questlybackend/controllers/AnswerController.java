package com.example.questlybackend.controllers;

import com.example.questlybackend.dtos.AnswerDto;
import com.example.questlybackend.models.Answer;
import com.example.questlybackend.services.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/answers")
public class AnswerController {
    
    private final AnswerService answerService;
    
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long id) {
        Optional<Answer> answer = answerService.getAnswerById(id);
        return answer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/question/{questionId}")
    public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId, @RequestParam int page, @RequestParam int size) {
        return answerService.getAnswersByQuestionId(questionId, page, size);
    }

    @PostMapping
    public Answer createAnswer(@RequestBody AnswerDto answerDto) {
        return answerService.createAnswer(answerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}