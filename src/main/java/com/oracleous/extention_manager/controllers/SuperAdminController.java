package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.SuperAdminRegRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SuperAdminController {
    @PostMapping("/super_admin_registration")
    public void agriBusinessRegistration(@RequestBody SuperAdminRegRequest superAdminRegRequest){

    }
}
