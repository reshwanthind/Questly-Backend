package com.example.questlybackend.repositories;

import com.example.questlybackend.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByQuestionId(Long questionId, Pageable pageable);
    Page<Comment> findByAnswerId(Long answerId, Pageable pageable);
    Page<Comment> findByAnswerIdAndQuestionId(Long answerId, Long questionId, Pageable pageable);
    Page<Comment> findByParentCommentId(Long parentCommentId, Pageable pageable);
}
