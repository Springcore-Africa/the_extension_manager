package com.oracleous.extention_manager.dto.requests.registrationRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalAdminRegRequest {
    private String firstName;
    private String lastName;

    private String phoneNumber;

    private String email;

    private String password;

    private String shortBio;

    private String passportPhotograph;
}
