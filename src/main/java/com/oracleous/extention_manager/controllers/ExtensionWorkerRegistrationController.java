package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.registrationRequest.ExtensionWorkerAdminDecision;
import com.oracleous.extention_manager.dto.requests.registrationRequest.ExtensionWorkerRequest;
import com.oracleous.extention_manager.dto.requests.registrationRequest.TokenVerificationRequest;
import com.oracleous.extention_manager.dto.response.extensionWorkerResponse.ExtensionWorkerResponse;
import com.oracleous.extention_manager.services.ExtensionWorker.ExtensionWorkerRegistration;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/extension-worker")
@RequiredArgsConstructor
@Tag(name = "Extension Worker Registration", description = "Endpoints for registering and managing extension workers")
public class ExtensionWorkerRegistrationController {

    private final ExtensionWorkerRegistration extensionWorkerService;

    @Operation(
            summary = "Register a new extension worker",
            description = "Submit extension worker registration details to initiate registration."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Extension worker registered successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExtensionWorkerResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid registration data",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            )
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerExtensionWorker(@RequestBody ExtensionWorkerRequest request) {
        try {
            ExtensionWorkerResponse response = extensionWorkerService.extensionWorker(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Verify registration token",
            description = "Verify the token sent by email during registration to confirm identity."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Token verified successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExtensionWorkerRequest.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid token or verification failed",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            )
    })
    @PostMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestBody TokenVerificationRequest request) {
        try {
            ExtensionWorkerRequest verifiedData = extensionWorkerService.verifyToken(request);
            return new ResponseEntity<>(verifiedData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(
            summary = "Get pending extension worker details for admin review",
            description = "Retrieve details of a pending extension worker by email for admin approval."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pending extension worker details retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExtensionWorkerRequest.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied - Admin role required",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pending extension worker not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/pending/{email}")
    public ResponseEntity<?> getPendingWorkerDetails(@PathVariable String email) {
        try {
            ExtensionWorkerRequest pendingWorker = extensionWorkerService.getPendingWorkerDetails(email);
            return new ResponseEntity<>(pendingWorker, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Admin approves or rejects an extension worker",
            description = "Process admin decision to approve or reject an extension worker registration."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Decision processed successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid decision data",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied - Admin role required",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/decision")
    public ResponseEntity<?> processAdminDecision(@RequestBody ExtensionWorkerAdminDecision decision) {
        try {
            String message = extensionWorkerService.approveOrRejectExtensionWorker(decision);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
