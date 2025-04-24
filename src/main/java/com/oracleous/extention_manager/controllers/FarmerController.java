package com.oracleous.extention_manager.controllers; // Adjust package as needed

import com.oracleous.extention_manager.dto.requests.registrationRequest.FarmerVerifyTokenRequest;
import com.oracleous.extention_manager.dto.requests.registrationRequest.FarmersRegistrationRequest;
import com.oracleous.extention_manager.dto.response.registrationResponse.FarmerResponse;
import com.oracleous.extention_manager.services.farmersServices.FarmerRegistration.FarmersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;

import static com.oracleous.extention_manager.utilities.ApplicationUtilities.ACCOUNT_CREATED_CODE;
import static com.oracleous.extention_manager.utilities.ApplicationUtilities.TOKEN_SENT_CODE;

@RestController
@RequestMapping("/api/v1/farmers")
@RequiredArgsConstructor
@Validated
public class FarmerController {

    private final FarmersService farmersService;
    @Operation(
            summary = "Farmer Registration",
            description = "Initiates farmer registration and sends a verification token to the provided email."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Registration initiated, check email for token",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FarmerResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request or farmer already exists",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FarmerResponse.class))
            )
    })
    @PostMapping("/register")
    public ResponseEntity<FarmerResponse> registerFarmer(@Valid @RequestBody FarmersRegistrationRequest request) {
        FarmerResponse response = farmersService.registerFarmer(request);
        return new ResponseEntity<>(response, response.getResponseCode().equals(TOKEN_SENT_CODE) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @Operation(
            summary = "Verify Registration Token",
            description = "Verifies the token sent to the farmer's email to complete registration."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Token verified, farmer registered successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FarmerResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid or expired token",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FarmerResponse.class))
            )
    })
    @PostMapping("/verify-token")
    public ResponseEntity<FarmerResponse> verifyToken(@RequestBody FarmerVerifyTokenRequest farmerVerifyTokenRequest) {
        FarmerResponse response = farmersService.verifyToken(farmerVerifyTokenRequest);
        return new ResponseEntity<>(response, response.getResponseCode().equals(ACCOUNT_CREATED_CODE) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}