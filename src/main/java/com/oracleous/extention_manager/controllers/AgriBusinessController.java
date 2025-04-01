package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.AgriBusinessRegRequest;
import com.oracleous.extention_manager.dto.response.AgriBusinessResponse;
import com.oracleous.extention_manager.exceptions.BusinessAlreadyExistsException;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundException;
import com.oracleous.extention_manager.services.agriBusinessServices.AgriBusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/agri_business")
public class AgriBusinessController {
    final AgriBusinessService agriBusinessService;

    @PostMapping("/agri_business_registration")
    public AgriBusinessResponse agriBusinessRegistration(@RequestBody AgriBusinessRegRequest agriBusinessRegRequest) throws BusinessAlreadyExistsException, FarmerNotFoundException {
       return agriBusinessService.registerAgriBusiness(agriBusinessRegRequest);



    }
}
