package com.oracleous.extention_manager.services.loginService;

import com.oracleous.extention_manager.dto.requests.loginRequest.LoginRequest;
import com.oracleous.extention_manager.dto.response.loginResponse.LoginResponse;
import com.oracleous.extention_manager.securityConfiguration.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginMethod implements Login{
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public LoginResponse loginResponse(LoginRequest loginRequest) {
        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            throw new IllegalArgumentException("Email and password are required");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        String token = jwtService.GenerateToken(loginRequest.getEmail());
        return LoginResponse.builder()
                .token(token)
                .message("Login Successful")
                .build();
    }
}
