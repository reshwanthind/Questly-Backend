package com.example.questlybackend.services;

import com.example.questlybackend.dtos.AnswerDto;
import com.example.questlybackend.models.Answer;
import com.example.questlybackend.models.Question;
import com.example.questlybackend.models.User;
import com.example.questlybackend.repositories.AnswerRepository;
import com.example.questlybackend.repositories.QuestionRepository;
import com.example.questlybackend.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository,
                         QuestionRepository questionRepository,
                         UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public Optional<Answer> getAnswerById(Long id) {
        return answerRepository.findById(id);
    }

    public List<Answer> getAnswersByQuestionId(Long questionId, int page, int size) {
        return answerRepository.findByQuestionId(questionId, PageRequest.of(page, size)).getContent();
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    public Answer createAnswer(AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setContent(answerDto.getContent());

        Optional<Question> question = questionRepository.findById(answerDto.getQuestionId());
        question.ifPresent(answer::setQuestion);

        Optional<User> user = userRepository.findById(answerDto.getUserId());
        user.ifPresent(answer::setUser);

        return answerRepository.save(answer);
    }
}