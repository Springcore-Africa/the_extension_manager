package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.services.agriBusinessServices.AdminReadAllAgriBusiness.AdminToViewAllAgriBusinessMethod;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Tag(name = "Admin - AgriBusiness", description = "Endpoints for admin to view agribusinesses")
public class AdminViewAgribusinessController {

    private final AdminToViewAllAgriBusinessMethod adminToViewAllAgriBusinessMethod;

    @GetMapping("/view/agriBusiness")
    @Operation(
            summary = "Admin views all registered agribusinesses",
            description = "Returns a list of all agribusinesses visible to the admin"
    )
    public ResponseEntity<?> viewAgriBusiness() {
        try {
            return ResponseEntity.ok(adminToViewAllAgriBusinessMethod.adminViewAgriBusinessResponse());
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }
}
