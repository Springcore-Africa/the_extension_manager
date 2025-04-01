package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.AgriBusinessRegRequest;
import com.oracleous.extention_manager.dto.requests.InvestorRegistrationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class InvestorController {
    @PostMapping("/investor_registration")
    public void agriBusinessRegistration(@RequestBody InvestorRegistrationRequest investorRegistrationRequest){

    }
}
