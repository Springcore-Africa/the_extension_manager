package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.AgriBusinessRegRequest;
import com.oracleous.extention_manager.dto.requests.InvestorRegistrationRequest;
import com.oracleous.extention_manager.dto.response.InvestorRegistrationResponse;
import com.oracleous.extention_manager.services.investorServices.InvestorServiceReg;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class InvestorController {
    private final InvestorServiceReg investorServiceReg;


    @PostMapping("/investor_registration")
    public ResponseEntity<?> investorRegistration(@RequestBody InvestorRegistrationRequest investorRegistrationRequest){
        try {
            return new ResponseEntity<>(investorServiceReg.investorRegistration(investorRegistrationRequest), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>("Registration failed: " + exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}