package com.oracleous.extention_manager.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data

public class RegistrationToken {
    @Id
    private String token;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}