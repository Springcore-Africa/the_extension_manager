package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.registrationRequest.SuperAdminRegRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.SuperAdminResponse;
import com.oracleous.extention_manager.services.superAdminServices.superAdminRegistration.SuperAdminRegistration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "SuperAdmin Registration", description = "API for registering SuperAdmin users")
public class SuperAdminController {
    private final SuperAdminRegistration superAdminRegistration;

    @Operation(
            summary = "Register a SuperAdmin",
            description = "Creates a new SuperAdmin account with the provided details. Returns a success message if registration is successful or an error if the email already exists."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "SuperAdmin registered successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuperAdminResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request or email already exists",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuperAdminResponse.class))
            )
    })

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