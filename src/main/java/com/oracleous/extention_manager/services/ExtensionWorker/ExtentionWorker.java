package com.oracleous.extention_manager.services.ExtensionWorker;

import com.oracleous.extention_manager.data.model.Stamp;
import com.oracleous.extention_manager.dto.requests.registrationRequest.ExtensionWorkerRequest;
import com.oracleous.extention_manager.dto.requests.registrationRequest.TokenVerificationRequest;
import com.oracleous.extention_manager.dto.response.extensionWorkerResponse.ExtensionWorkerResponse;

public interface ExtentionWorker {
    ExtensionWorkerResponse extensionWorker(ExtensionWorkerRequest extensionWorkerRequest);
    ExtensionWorkerRequest verifyToken(TokenVerificationRequest tokenVerificationRequest);
    ExtensionWorkerRequest getPendingWorkerDetails(String email);

    String approveExtensionWorker(String email, Stamp action);
}