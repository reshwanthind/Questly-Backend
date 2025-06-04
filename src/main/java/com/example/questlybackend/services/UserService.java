package com.example.questlybackend.services;
import com.example.questlybackend.dtos.UserDto;
import com.example.questlybackend.models.Question;
import com.example.questlybackend.models.Tag;
import com.example.questlybackend.models.User;
import com.example.questlybackend.repositories.QuestionRepository;
import com.example.questlybackend.repositories.TagRepository;
import com.example.questlybackend.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final QuestionRepository questionRepository;
    
    public UserService(UserRepository userRepository, 
                       TagRepository tagRepository,
                       QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.questionRepository = questionRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    public void followTag(Long userId, Long tagId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));
        user.getFollowedTags().add(tag);
        userRepository.save(user);
    }

    public List<Question> getUserFeed(Long userId, int page, int size) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Set<Long> tagIds = user.getFollowedTags().stream().map(Tag::getId).collect(Collectors.toSet());

        return questionRepository.findQuestionsByTags(tagIds, PageRequest.of(page, size)).getContent();
    }



}