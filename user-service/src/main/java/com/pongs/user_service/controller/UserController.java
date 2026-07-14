package com.pongs.user_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pongs.user_service.model.dto.request.UserRequest;
import com.pongs.user_service.model.dto.response.UserResponse;
import com.pongs.user_service.model.entity.User;
import com.pongs.user_service.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserResponse.from(
                    userService.addUser(request.getUsername().toString(), request.getFirstName().toString(), request.getLastName().toString())
                ));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getStock(@PathVariable String username) {
        
        User user = userService.getUser(username);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(UserResponse.from(user));

    }
}
