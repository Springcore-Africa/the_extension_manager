package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.registrationRequest.LocalAdminRegRequest;
import com.oracleous.extention_manager.services.localAdminService.localAdminRegistration.LocalAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/local_admin")
@RequiredArgsConstructor
@Tag(name = "Local Admin Registration", description = "Endpoints for local admin registration")
public class LocalAdminController {

    private final LocalAdminService localAdminService;

    @Operation(
            summary = "Register a new local admin",
            description = "Registers a local admin using the provided registration details."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Local admin successfully registered",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid registration data or error occurred",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            )
    })
    @PostMapping("/registration")
    public ResponseEntity<?> agriBusinessRegistration(@RequestBody LocalAdminRegRequest localAdminRegRequest) {
        try {
            Object response = localAdminService.localAdminRegistration(localAdminRegRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
