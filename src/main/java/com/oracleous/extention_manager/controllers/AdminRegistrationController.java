package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.requestEmail.AdminRegistrationRequestDto;
import com.oracleous.extention_manager.dto.response.ResponseToMailSend.InitiateAdminRegistration;
import com.oracleous.extention_manager.services.adminService.AdminRegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Slf4j
public class AdminRegistrationController {
    private final AdminRegistrationService registrationService;

//    @Value("${app.superadmin-email:ajadi1taoreed10@gmail.com}")
//    private String superAdminEmail;



    @PostMapping("/register/initiate")
  public ResponseEntity <?> initiateAdminRegistration(@RequestBody @Valid AdminRegistrationRequestDto adminRegistrationRequestDto) {
        String superAdmin = "ajaditaoreed10@gmail.com";
        try {
            return new ResponseEntity<>(registrationService.initiateAdminRegistration(adminRegistrationRequestDto, superAdmin),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
//    public ResponseEntity<InitiateAdminRegistration> initiateRegistration(@Valid @RequestBody AdminRegistrationRequestDto request) {
//        try {
//            InitiateAdminRegistration response = registrationService.initiateAdminRegistration(request, superAdminEmail);
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//            log.error("Validation error: {}", e.getMessage());
//            return new ResponseEntity<>(InitiateAdminRegistration.builder()
//                    .message(e.getMessage())
//                    .build(), HttpStatus.BAD_REQUEST);
//        } catch (SecurityException e) {
//            log.error("Authorization error: {}", e.getMessage());
//            return new ResponseEntity<>(InitiateAdminRegistration.builder()
//                    .message("Unauthorized: Only SuperAdmin can initiate registration")
//                    .build(), HttpStatus.FORBIDDEN);
//        } catch (Exception e) {
//            log.error("Unexpected error: {}", e.getMessage(), e);
//            return new ResponseEntity<>(InitiateAdminRegistration.builder()
//                    .message("Internal server error")
//                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}