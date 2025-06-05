package com.example.questlybackend.controllers;

import com.example.questlybackend.dtos.UserDto;
import com.example.questlybackend.models.User;
import com.example.questlybackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{userId}/followTag/{tagId}")
    public ResponseEntity<Void> followTag(@PathVariable Long userId, @PathVariable Long tagId) {
        userService.followTag(userId, tagId);
        return ResponseEntity.noContent().build();
    }

}