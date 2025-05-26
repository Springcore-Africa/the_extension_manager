package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.data.model.RegistrationToken;
import com.oracleous.extention_manager.dto.requests.requestEmail.AdminRegistrationRequestDto;
import com.oracleous.extention_manager.dto.requests.requestEmail.AdminCompletionRequestDto;
import com.oracleous.extention_manager.dto.response.ResponseToMailSend.CompleteAdminRegistration;
import com.oracleous.extention_manager.dto.response.ResponseToMailSend.InitiateAdminRegistration;
import com.oracleous.extention_manager.services.adminService.adminRegistration.AdminRegistrationService;
import com.oracleous.extention_manager.services.adminService.completeRegistration.CompleteAdminRegMethod;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Slf4j
@Tag(name = "Initiate Admin Registration and Completion API", description = "")
public class AdminRegistrationController {
    @Autowired
    private AdminRegistrationService adminRegistrationService;
    @Autowired
    private final CompleteAdminRegMethod completeAdminRegistration;

    @Operation(
            summary = "Initiate Admin Registration",
            description = "Initiates admin registration by validating the SuperAdmin's email, checking for duplicate emails, generating a 2-hour token, and sending a registration email."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Registration initiated, check email for token",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = InitiateAdminRegistration.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid email format or email already exists",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = InitiateAdminRegistration.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Unauthorized, SuperAdmin access required",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = InitiateAdminRegistration.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = InitiateAdminRegistration.class))
            )
    })
    @PostMapping("/register/initiate")
    public ResponseEntity<InitiateAdminRegistration> initiateAdminRegistration(@Valid @RequestBody AdminRegistrationRequestDto adminRegistrationRequestDto) {
        String superAdminEmail = "ajaditaoreed@gmail.com";
        try {
            InitiateAdminRegistration response = adminRegistrationService.initiateAdminRegistration(adminRegistrationRequestDto, superAdminEmail);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            log.error("Validation error: {}", e.getMessage());
            return new ResponseEntity<>(InitiateAdminRegistration.builder()
                    .message(e.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        } catch (SecurityException e) {
            log.error("Authorization error: {}", e.getMessage());
            return new ResponseEntity<>(InitiateAdminRegistration.builder()
                    .message("Unauthorized: Only SuperAdmin can initiate registration")
                    .build(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage(), e);
            return new ResponseEntity<>(InitiateAdminRegistration.builder()
                    .message("Internal server error")
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Show Admin Registration Form",
            description = "Returns an HTML form for completing admin registration if the provided token is valid (within 2 hours) and the admin email is registered."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTML form for completing registration",
                    content = @Content(mediaType = "text/html", schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid or expired token, or admin not found",
                    content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))
            )
    })
    @GetMapping("/register/complete-form")
    public ResponseEntity<String> showCompleteRegistrationForm(@RequestParam("token") String token) {
        try {
            RegistrationToken tokenData = adminRegistrationService.validateToken(token);
            if (tokenData == null) {
                return new ResponseEntity<>("Specify duration has expired or invalid token", HttpStatus.BAD_REQUEST);
            }
            String email = tokenData.getEmail();
            if (adminRegistrationService.isAdminEmailRegistered(email)) {
                return ResponseEntity.ok(
                        "<html><body>" +
                                "<h2>Complete Admin Registration</h2>" +
                                "<form id='registrationForm' onsubmit='submitForm(event)'>" +
                                "<input type='hidden' id='token' value='" + token + "'/>" +
                                "<input type='hidden' id='email' value='" + email + "'/>" +
                                "<label>Name: <input type='text' id='name' required/></label><br/>" +
                                "<label>Password: <input type='password' id='password' required/></label><br/>" +
                                "<input type='submit' value='Complete Registration'/>" +
                                "</form>" +
                                "<script>" +
                                "function submitForm(event) {" +
                                "  event.preventDefault();" +
                                "  const data = {" +
                                "    token: document.getElementById('token').value," +
                                "    email: document.getElementById('email').value," +
                                "    name: document.getElementById('name').value," +
                                "    password: document.getElementById('password').value" +
                                "  };" +
                                "  fetch('/admin/register/complete', {" +
                                "    method: 'POST'," +
                                "    headers: {'Content-Type': 'application/json'}," +
                                "    body: JSON.stringify(data)" +
                                "  }).then(response => {" +
                                "    if (!response.ok) {" +
                                "      return response.json().then(data => { throw new Error(data.message || 'Request failed'); });" +
                                "    }" +
                                "    return response.json();" +
                                "  }).then(data => {" +
                                "    alert(data.message);" +
                                "    window.location.href = '/admin/registration-success';" +
                                "  }).catch(error => {" +
                                "    alert('Error: ' + error.message);" +
                                "  });" +
                                "}" +
                                "</script>" +
                                "</body></html>"
                );
            } else {
                return new ResponseEntity<>("Admin not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Error showing form: {}", e.getMessage());
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Complete Admin Registration",
            description = "Finalizes admin registration by validating the token, email, name, and password. Token must be valid and not expired (within 2 hours)."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Registration completed successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompleteAdminRegistration.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input or registration state",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompleteAdminRegistration.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompleteAdminRegistration.class))
            )
    })
    @PostMapping("/register/complete")
    public ResponseEntity<CompleteAdminRegistration> completeRegistration(@Valid @RequestBody AdminCompletionRequestDto request) {
        try {
            CompleteAdminRegistration response = completeAdminRegistration.completeAdminRegistration(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Validation error: {}", e.getMessage());
            return new ResponseEntity<>(CompleteAdminRegistration.builder()
                    .message(e.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException e) {
            log.error("State error: {}", e.getMessage());
            return new ResponseEntity<>(CompleteAdminRegistration.builder()
                    .message(e.getMessage())
                    .build(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage(), e);
            return new ResponseEntity<>(CompleteAdminRegistration.builder()
                    .message("Internal server error")
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Show Registration Success Page",
            description = "Returns an HTML page confirming successful admin registration."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Registration success page displayed",
                    content = @Content(mediaType = "text/html", schema = @Schema(implementation = String.class))
            )
    })
    @GetMapping("/registration-success")
    public ResponseEntity<String> showRegistrationSuccess() {
        return ResponseEntity.ok(
                "<html><body>" +
                        "<h2>Registration Successful</h2>" +
                        "<p>Your admin account has been successfully created.</p>" +
                        "</body></html>"
        );
    }





}