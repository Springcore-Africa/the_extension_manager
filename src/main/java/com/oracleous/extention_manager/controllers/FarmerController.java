package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.FarmersRegistrationRequest;
import com.oracleous.extention_manager.dto.response.FarmerResponse;
import com.oracleous.extention_manager.services.farmersServices.FarmerRegistration.FarmerServiceImplementation;
import com.oracleous.extention_manager.services.farmersServices.FarmerRegistration.FarmersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farmers")
@RequiredArgsConstructor
public class FarmerController {
    final FarmerServiceImplementation farmerServiceImplementation;
    final FarmersService farmersService;

    @PostMapping("/farmers_registration")
    public FarmerResponse farmersRegistration(@RequestBody FarmersRegistrationRequest farmersRegistrationRequest) {
        return farmersService.registerFarmer(farmersRegistrationRequest);
    }
}
