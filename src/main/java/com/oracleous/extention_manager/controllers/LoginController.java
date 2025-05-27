package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.loginRequest.LoginRequest;
import com.oracleous.extention_manager.services.loginService.LoginMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class LoginController {
    private final LoginMethod login ;

    @PostMapping("/login")
    public ResponseEntity <?> login(@RequestBody LoginRequest loginRequest) {
        try{
            return new ResponseEntity<>(login.loginResponse(loginRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
