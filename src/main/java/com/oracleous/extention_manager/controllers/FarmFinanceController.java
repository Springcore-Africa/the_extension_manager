package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.dto.requests.farmPackage.FarmFinanceRequestDto;
import com.oracleous.extention_manager.dto.response.farmResponse.FarmFinanceResponseDto;
import com.oracleous.extention_manager.services.farmService.FarmFinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farmer")
@RequiredArgsConstructor
public class FarmFinanceController {

    private final FarmFinanceService farmFinanceService;

    @PostMapping("/farm-finance")
    public ResponseEntity<FarmFinanceResponseDto> calculateFinance(@RequestBody FarmFinanceRequestDto request) {
        FarmFinanceResponseDto response = farmFinanceService.calculateFarmFinance(request);
        return ResponseEntity.ok(response);
    }
}
