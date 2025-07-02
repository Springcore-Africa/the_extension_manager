package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.loginRequest.LoginRequest;
import com.oracleous.extention_manager.services.loginService.LoginMethod;
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
@RequestMapping("/user/")
@RequiredArgsConstructor
@Tag(name = "User Authentication", description = "User login endpoint")
public class LoginController {

    private final LoginMethod login;

    @Operation(
            summary = "User Login",
            description = "Authenticates user and returns login response with tokens or user info."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User successfully logged in",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Object.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error during login",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            )
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Object response = login.loginResponse(loginRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
