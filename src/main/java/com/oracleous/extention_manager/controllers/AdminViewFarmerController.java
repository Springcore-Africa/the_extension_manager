package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.adminViewFarmer.AdminViewFarmersRequest;
import com.oracleous.extention_manager.services.farmersServices.AdminReadFarmers.AdminToReadAllFarmerMethod;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "Admin - Farmer", description = "Endpoints for admin to view farmer")
public class AdminViewFarmerController {
    private final AdminToReadAllFarmerMethod adminToReadAllFarmerMethod;

    @GetMapping("/view/farmer")
    @Operation(
            summary = "Admin views registered farmer",
            description = "Returns the details ofa farmer to admin"
    )
    public ResponseEntity<?> viewFarmer(@RequestBody AdminViewFarmersRequest adminViewFarmersRequest) {
        try {
            return new ResponseEntity<>(adminToReadAllFarmerMethod.adminViewFarmers(adminViewFarmersRequest), HttpStatus.OK);
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }

}
