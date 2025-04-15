//package com.oracleous.extention_manager.controllers;
//
//import com.oracleous.extention_manager.dto.requests.requestEmail.AdminRegistrationRequestDto;
//import com.oracleous.extention_manager.dto.requests.requestEmail.AdminCompletionRequestDto;
//import com.oracleous.extention_manager.dto.response.ResponseToMailSend.CompleteAdminRegistration;
//import com.oracleous.extention_manager.dto.response.ResponseToMailSend.InitiateAdminRegistration;
//import com.oracleous.extention_manager.services.adminService.AdminRegistrationService;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/admin")
//@AllArgsConstructor
//@Slf4j
//public class AdminRegistrationController {
//    private final AdminRegistrationService registrationService;
//
//    @PostMapping("/register/initiate")
//    public ResponseEntity<InitiateAdminRegistration> initiateAdminRegistration(@RequestBody @Valid AdminRegistrationRequestDto adminRegistrationRequestDto) {
//        String superAdminEmail = "ajaditaoreed10@gmail.com";
//        try {
//            InitiateAdminRegistration response = registrationService.initiateAdminRegistration(adminRegistrationRequestDto, superAdminEmail);
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
//
//    @GetMapping("/register/complete-form")
//    public ResponseEntity<String> showCompleteRegistrationForm(@RequestParam("email") String email) {
//        try {
//            if (registrationService.isAdminEmailRegistered(email)) {
//                return ResponseEntity.ok(
//                        "<html><body>" +
//                                "<h2>Complete Admin Registration</h2>" +
//                                "<form id='registrationForm' onsubmit='submitForm(event)'>" +
//                                "<input type='hidden' id='email' value='" + email + "'/>" +
//                                "<label>Name: <input type='text' id='name' required/></label><br/>" +
//                                "<label>Password: <input type='password' id='password' required/></label><br/>" +
//                                "<input type='submit' value='Complete Registration'/>" +
//                                "</form>" +
//                                "<script>" +
//                                "function submitForm(event) {" +
//                                "  event.preventDefault();" +
//                                "  const data = {" +
//                                "    email: document.getElementById('email').value," +
//                                "    name: document.getElementById('name').value," +
//                                "    password: document.getElementById('password').value" +
//                                "  };" +
//                                "  fetch('/admin/register/complete', {" +
//                                "    method: 'POST'," +
//                                "    headers: {'Content-Type': 'application/json'}," +
//                                "    body: JSON.stringify(data)" +
//                                "  }).then(response => response.json()).then(data => {" +
//                                "    alert(data.message);" +
//                                "    if (response.ok) window.location.href = '/admin/registration-success';" +
//                                "  }).catch(error => {" +
//                                "    alert('Error: ' + error);" +
//                                "  });" +
//                                "}" +
//                                "</script>" +
//                                "</body></html>"
//                );
//            } else {
//                return new ResponseEntity<>("Admin not found", HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            log.error("Error showing form: {}", e.getMessage());
//            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("/register/complete")
//    public ResponseEntity<CompleteAdminRegistration> completeRegistration(@Valid @RequestBody AdminCompletionRequestDto request) {
//        try {
//            CompleteAdminRegistration response = registrationService.completeAdminRegistration(request);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            log.error("Validation error: {}", e.getMessage());
//            return new ResponseEntity<>(CompleteAdminRegistration.builder()
//                    .message(e.getMessage())
//                    .build(), HttpStatus.BAD_REQUEST);
//        } catch (IllegalStateException e) {
//            log.error("State error: {}", e.getMessage());
//            return new ResponseEntity<>(CompleteAdminRegistration.builder()
//                    .message(e.getMessage())
//                    .build(), HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            log.error("Unexpected error: {}", e.getMessage(), e);
//            return new ResponseEntity<>(CompleteAdminRegistration.builder()
//                    .message("Internal server error")
//                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/registration-success")
//    public ResponseEntity<String> showRegistrationSuccess() {
//        return ResponseEntity.ok(
//                "<html><body>" +
//                        "<h2>Registration Successful</h2>" +
//                        "<p>Your admin account has been successfully created.</p>" +
//                        "</body></html>"
//        );
//    }
//}

package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.requestEmail.AdminRegistrationRequestDto;
import com.oracleous.extention_manager.dto.requests.requestEmail.AdminCompletionRequestDto;
import com.oracleous.extention_manager.dto.response.ResponseToMailSend.CompleteAdminRegistration;
import com.oracleous.extention_manager.dto.response.ResponseToMailSend.InitiateAdminRegistration;
import com.oracleous.extention_manager.services.adminService.AdminRegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Slf4j
public class AdminRegistrationController {
    private final AdminRegistrationService registrationService;

    @PostMapping("/register/initiate")
    public ResponseEntity<InitiateAdminRegistration> initiateAdminRegistration(@RequestBody @Valid AdminRegistrationRequestDto adminRegistrationRequestDto) {
        String superAdminEmail = "ajaditaoreed10@gmail.com";
        try {
            InitiateAdminRegistration response = registrationService.initiateAdminRegistration(adminRegistrationRequestDto, superAdminEmail);
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

    @GetMapping("/register/complete-form")
    public ResponseEntity<String> showCompleteRegistrationForm(@RequestParam("email") String email) {
        try {
            if (registrationService.isAdminEmailRegistered(email)) {
                return ResponseEntity.ok(
                        "<html><body>" +
                                "<h2>Complete Admin Registration</h2>" +
                                "<form id='registrationForm' onsubmit='submitForm(event)'>" +
                                "<input type='hidden' id='email' value='" + email + "'/>" +
                                "<label>Name: <input type='text' id='name' required/></label><br/>" +
                                "<label>Password: <input type='password' id='password' required/></label><br/>" +
                                "<input type='submit' value='Complete Registration'/>" +
                                "</form>" +
                                "<script>" +
                                "function submitForm(event) {" +
                                "  event.preventDefault();" +
                                "  const data = {" +
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
                                "      throw new Error('Request failed with status ' + response.status);" +
                                "    }" +
                                "    return response.json();" +
                                "  }).then(data => {" +
                                "    alert(data.message);" +
                                "    window.location.href = '/admin/registration-success';" +
                                "  }).catch(error => {" +
                                "    alert('Error: ' + error);" +
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

    @PostMapping("/register/complete")
    public ResponseEntity<CompleteAdminRegistration> completeRegistration(@Valid @RequestBody AdminCompletionRequestDto request) {
        try {
            CompleteAdminRegistration response = registrationService.completeAdminRegistration(request);
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