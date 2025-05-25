package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.registrationRequest.ExtensionWorkerRequest;
import com.oracleous.extention_manager.dto.requests.registrationRequest.TokenVerificationRequest;
import com.oracleous.extention_manager.dto.response.extensionWorkerResponse.ExtensionWorkerResponse;
import com.oracleous.extention_manager.services.ExtensionWorker.ExtensionWorkerRegistration;
import com.oracleous.extention_manager.data.model.Stamp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/extension-worker")
@RequiredArgsConstructor
public class ExtensionWorkerRegistrationController {

    private final ExtensionWorkerRegistration extensionWorkerService;

    /**
     * Register a new ExtensionWorker
     */
    @PostMapping("/register")
    public ResponseEntity<ExtensionWorkerResponse> registerExtensionWorker(
            @RequestBody ExtensionWorkerRequest request) {
        ExtensionWorkerResponse response = extensionWorkerService.extensionWorker(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Verify registration token (via email link)
     */
    @PostMapping("/verify")
    public ResponseEntity<ExtensionWorkerRequest> verifyToken(
            @RequestBody TokenVerificationRequest request) {
        ExtensionWorkerRequest verifiedData = extensionWorkerService.verifyToken(request);
        return ResponseEntity.ok(verifiedData);
    }

    /**
     * Get pending ExtensionWorker details for admin review
     */
    @GetMapping("/pending/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExtensionWorkerRequest> getPendingWorkerDetails(
            @PathVariable String email) {
        ExtensionWorkerRequest pendingWorker = extensionWorkerService.getPendingWorkerDetails(email);
        return ResponseEntity.ok(pendingWorker);
    }

    /**
     * Admin approval or rejection of the worker
     */
    @PostMapping("/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> approveExtensionWorker(
            @RequestParam("email") String email,
            @RequestParam("action") Stamp action) {
        String result = extensionWorkerService.approveExtensionWorker(email, action);
        return ResponseEntity.ok(result);
    }
}