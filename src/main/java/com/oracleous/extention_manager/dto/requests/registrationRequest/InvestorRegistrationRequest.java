package com.oracleous.extention_manager.dto.requests.registrationRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request payload to register investor")
public class InvestorRegistrationRequest {

    @Schema(description = "firstname", example = "god", required = true)
    private String firstName;

    @Schema(description = "lastname", example = "god", required = true)
    private String lastName;

    @Schema(description = "user cell number", example = "09011234567", required = true)
    private String phoneNumber;

    @Schema(description = "user email", example = "god@gmail.com", required = true)
    private String email;

    @Schema(description = "user password", example = "1010", required = true)
    private String password;

    @Schema(description = "a brief description of user", example = "i am a user and born in lagos", required = true)
    private String shortBio;

    @Schema(description = "user upload image here ", example = "jpg", required = true)
    private String passportPhotograph;
}
