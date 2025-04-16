package com.oracleous.extention_manager.dto.requests.registrationRequest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request payload to register investor")
public class InvestorRegistrationRequest {

    private String firstName;

    private String lastName;

    private String phoneNumber;
    private String email;
    private String password;
    private String shortBio;
    private String passportPhotograph;
}
