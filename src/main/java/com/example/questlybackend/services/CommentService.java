package com.example.questlybackend.services;

import com.example.questlybackend.dtos.CommentDto;
import com.example.questlybackend.models.Answer;
import com.example.questlybackend.models.Comment;
import com.example.questlybackend.repositories.AnswerRepository;
import com.example.questlybackend.repositories.CommentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;

    public CommentService(CommentRepository commentRepository,
                          AnswerRepository answerRepository) {
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getCommentsByAnswerId(Long answerId, int page, int size) {
        return commentRepository.findByAnswerId(answerId, PageRequest.of(page, size)).getContent();
    }

    public List<Comment> getRepliesByCommentId(Long commentId, int page, int size) {
        return commentRepository.findByParentCommentId(commentId, PageRequest.of(page, size)).getContent();
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public Comment createComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());

        Optional<Answer> answer = answerRepository.findById(commentDto.getAnswerId());
        answer.ifPresent(comment::setAnswer);

        if (commentDto.getParentCommentId() != null) {
            Optional<Comment> parentComment = commentRepository.findById(commentDto.getParentCommentId());
            parentComment.ifPresent(comment::setParentComment);
        }

        return commentRepository.save(comment);
    }
}