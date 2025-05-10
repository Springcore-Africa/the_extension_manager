package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.registrationRequest.AgriBusinessRegRequest;
import com.oracleous.extention_manager.dto.requests.readRequest.AgricGetRequest;
import com.oracleous.extention_manager.dto.response.readResponse.AgricGetResponse;
import com.oracleous.extention_manager.dto.response.registrationResponse.AgriBusinessResponse;
import com.oracleous.extention_manager.dto.response.registrationResponse.SuperAdminResponse;
import com.oracleous.extention_manager.exceptions.BusinessAlreadyExistsException;
import com.oracleous.extention_manager.exceptions.FarmerNotFoundException;
import com.oracleous.extention_manager.services.agriBusinessServices.AgricBusinessReadPackage.GetAgricBusinessDetailsMethod;
//import com.oracleous.extention_manager.services.agriBusinessServices.AgricBusinessRegistration.AgriBusinessService;
import com.oracleous.extention_manager.services.agriBusinessServices.AgricBusinessRegistration.AgriBusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agri_business")
@Tag(name = "AgriBusiness Registration and retrieving API", description = "API for registering AgricBusiness and find AgricBusiness users")
public class AgriBusinessController {
    final AgriBusinessService agriBusinessService;
    final GetAgricBusinessDetailsMethod agricBusinessDetailsMethod ;

    @Operation(
            summary = "Register a AgriBusiness",
            description = "Creates a new AgricBusiness account with the provided details. Returns a success message if registration is successful or an error if the email already exists."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "AgricBusiness registered successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgriBusinessResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request or email already exists",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgriBusinessResponse.class))
            )
    })

    @PostMapping("/register")
    public ResponseEntity <?> agriBusinessRegistration(@RequestBody AgriBusinessRegRequest agriBusinessRegRequest) throws BusinessAlreadyExistsException, FarmerNotFoundException {
       try{
           return new ResponseEntity<>(agriBusinessService.registerAgriBusiness(agriBusinessRegRequest), HttpStatus.CREATED);
       }catch (BusinessAlreadyExistsException exception){
           return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }


    @Operation(
            summary = "To find AgriBusiness details",
            description = "End point to find agriBusiness details. Returns the details or an error if the invalid request are input."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "AgricBusiness details",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgricGetResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request or email already exists",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AgricGetResponse.class))
            )
    })

    @GetMapping("/find_agriBusiness/")
    public ResponseEntity <?> findAgriBusiness(@RequestBody AgricGetRequest agricGetRequest) {
        try{
            return new ResponseEntity<>(agricBusinessDetailsMethod.getAgricBusinessDetails(agricGetRequest), HttpStatus.FOUND);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
