package com.example.questlybackend.services;

import com.example.questlybackend.dtos.QuestionDto;
import com.example.questlybackend.models.Question;
import com.example.questlybackend.models.Tag;
import com.example.questlybackend.models.User;
import com.example.questlybackend.repositories.QuestionRepository;
import com.example.questlybackend.repositories.TagRepository;
import com.example.questlybackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final QuestionIndexService questionIndexService;

    public QuestionService(QuestionRepository questionRepository,
                           UserRepository userRepository,
                           TagRepository tagRepository,
                           QuestionIndexService questionIndexService) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.questionIndexService = questionIndexService;
    }

    public List<Question> getQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public Question createQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setTitle(questionDto.getTitle());
        question.setContent(questionDto.getContent());

        Optional<User> user = userRepository.findById(questionDto.getUserId());
        user.ifPresent(question::setUser);

        Set<Tag> tags = questionDto.getTagIds().stream()
                .map(tagRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        question.setTags(tags);



        Question savedQuestion = questionRepository.save(question);
        questionIndexService.indexQuestion(savedQuestion);

        return savedQuestion;
    }
}