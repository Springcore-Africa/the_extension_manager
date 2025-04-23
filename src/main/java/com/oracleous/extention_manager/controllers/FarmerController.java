package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.registrationRequest.FarmersRegistrationRequest;
import com.oracleous.extention_manager.dto.requests.readRequest.FarmerGetRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.FarmerResponse;
import com.oracleous.extention_manager.dto.response.registrationResponse.SuperAdminResponse;
import com.oracleous.extention_manager.services.farmersServices.FarmerReadPackage.GetFarmerDetailsMethod;
import com.oracleous.extention_manager.services.farmersServices.FarmerRegistration.FarmerServiceImplementation;
import com.oracleous.extention_manager.services.farmersServices.FarmerRegistration.FarmersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farmers")
@RequiredArgsConstructor
@Tag(name = "Farmer API", description = "Endpoints for farmer registration and retrieval")
public class FarmerController {
    final FarmerServiceImplementation farmerServiceImplementation;
    final FarmersService farmersService;
    final GetFarmerDetailsMethod getFarmerDetailsMethod ;


    @Operation(
            summary = "Farmer Registration",
            description = "Creates a new farmer account with the provided requirement. Returns a success message if registration is successful or an error if the email already exists."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "farmer registered successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FarmerResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request or email already exists",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FarmerResponse.class))
            )
    })
    @PostMapping("/farmers_registration")
    public ResponseEntity <?> farmersRegistration(@RequestBody FarmersRegistrationRequest farmersRegistrationRequest, String token) {
//            String token =
            try{
            return new ResponseEntity<>(farmerServiceImplementation.registerFarmer(farmersRegistrationRequest, token), HttpStatus.CREATED);
        }catch (Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
    }



    @Operation(
            summary = "Find Farmer Details",
            description = "Fetches the details of a registered farmer based on the provided request parameters."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved farmer details",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FarmerResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request parameters or farmer not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            )
    })

    @GetMapping("/find_farmer")
    public ResponseEntity<?> findFarmer(@RequestBody FarmerGetRequest farmerGetRequest) {
        try {
            return new ResponseEntity<>(getFarmerDetailsMethod.getFarmerDetails(farmerGetRequest), HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
