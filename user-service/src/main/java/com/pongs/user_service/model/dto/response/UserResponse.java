package com.pongs.user_service.model.dto.response;

import java.time.LocalDateTime;

import com.pongs.user_service.model.entity.User;

public record UserResponse (
    Long id,
    String username,
    String firstName,
    String lastName,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static UserResponse from(User user) {
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getFirstName(),
            user.getLastName(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
