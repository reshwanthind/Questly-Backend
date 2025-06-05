package com.example.questlybackend.controllers;

import com.example.questlybackend.dtos.CommentDto;
import com.example.questlybackend.models.Comment;
import com.example.questlybackend.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/answer/{answerId}")
    public List<Comment> getCommentsByAnswerId(@PathVariable Long answerId, @RequestParam int page, @RequestParam int size) {
        return commentService.getCommentsByAnswerId(answerId, page, size);
    }

    @GetMapping("/comment/{commentId}")
    public List<Comment> getRepliesByCommentId(@PathVariable Long commentId, @RequestParam int page, @RequestParam int size) {
        return commentService.getRepliesByCommentId(commentId, page, size);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentDto commentDto) {
        return commentService.createComment(commentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}