package com.example.questlybackend.controllers;

import com.example.questlybackend.models.Question;
import com.example.questlybackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feed")
public class UserFeedController {

    private final UserService userService;

    public UserFeedController(UserService UserService, UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Question>> getUserFeed(@PathVariable Long userId, @RequestParam int page, @RequestParam int size) {
        List<Question> feed = userService.getUserFeed(userId, page, size);
        return ResponseEntity.ok(feed);
    }
}
