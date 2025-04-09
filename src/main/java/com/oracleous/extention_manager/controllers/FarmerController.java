package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.FarmersRegistrationRequest;
import com.oracleous.extention_manager.dto.requests.readRequest.FarmerGetRequest;
import com.oracleous.extention_manager.dto.response.FarmerResponse;
import com.oracleous.extention_manager.services.farmersServices.FarmerReadPackage.GetFarmerDetailsMethod;
import com.oracleous.extention_manager.services.farmersServices.FarmerRegistration.FarmerServiceImplementation;
import com.oracleous.extention_manager.services.farmersServices.FarmerRegistration.FarmersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farmers")
@RequiredArgsConstructor
public class FarmerController {
    final FarmerServiceImplementation farmerServiceImplementation;
    final FarmersService farmersService;
    final GetFarmerDetailsMethod getFarmerDetailsMethod ;

    @PostMapping("/farmers_registration")
    public FarmerResponse farmersRegistration(@RequestBody FarmersRegistrationRequest farmersRegistrationRequest) {
        return farmersService.registerFarmer(farmersRegistrationRequest);
    }

    @GetMapping("/find_farmer/")
    public ResponseEntity<?> findFarmer(@RequestBody FarmerGetRequest farmerGetRequest) {
        try {
            return new ResponseEntity<>(getFarmerDetailsMethod.getFarmerDetails(farmerGetRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
