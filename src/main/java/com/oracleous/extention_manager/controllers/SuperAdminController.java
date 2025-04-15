package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.registrationRequest.SuperAdminRegRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.SuperAdminResponse;
import com.oracleous.extention_manager.services.superAdminServices.superAdminRegistration.SuperAdminRegistration;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SuperAdminController {
    private final SuperAdminRegistration superAdminRegistration;

    @PostMapping("/super_admin_registration")
    public ResponseEntity<SuperAdminResponse> superAdminRegistration(@RequestBody SuperAdminRegRequest superAdminRegRequest) {
        try {
            SuperAdminResponse response = superAdminRegistration.superAdminRegistration(superAdminRegRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(SuperAdminResponse.builder()
                    .message(exception.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }
}