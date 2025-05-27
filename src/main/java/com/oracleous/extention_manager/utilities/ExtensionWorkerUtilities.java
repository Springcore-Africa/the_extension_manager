package com.oracleous.extention_manager.utilities;

import com.oracleous.extention_manager.data.model.ExtensionWorker;
import com.oracleous.extention_manager.data.model.Stamp;
import com.oracleous.extention_manager.data.repositories.ExtensionWorkerRepository;
import com.oracleous.extention_manager.email.EmailEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.EventListener;

@Component
@AllArgsConstructor
public class ExtensionWorkerUtilities {
    private final ExtensionWorkerRepository extensionWorkerRepository;
    private final ApplicationEventPublisher eventPublisher;

//    public String approveExtensionWorker(String email, Stamp action) {
//        ExtensionWorker worker = extensionWorkerRepository.findByUsersEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        worker.setStamp(action);
//        extensionWorkerRepository.save(worker);
//
//        String subject = (action == Stamp.APPROVE) ? "Approved" : "Rejected";
//        String message = (action == Stamp.APPROVE)
//                ? "Your registration has been approved."
//                : "Your registration has been rejected.";
//
//        eventPublisher.publishEvent(new EmailEvent(this, email, subject, message));
//        return "Status updated to " + action.name();
//    }

}
