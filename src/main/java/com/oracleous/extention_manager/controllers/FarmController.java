package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.data.model.Farm;
import com.oracleous.extention_manager.data.repositories.FarmRepository;
import com.oracleous.extention_manager.dto.requests.farmPackage.FarmRequest;
import com.oracleous.extention_manager.dto.requests.farmPackage.FarmingCostDto;
import com.oracleous.extention_manager.services.farmService.FarmService;
import com.oracleous.extention_manager.services.farmService.FarmingCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farmer")
@RequiredArgsConstructor
public class FarmController {
    private final FarmService farmService;
    private final FarmingCostService farmingCostService;
    private final FarmRepository farmRepository;

    @PostMapping("/farm_registration")
    public ResponseEntity<?> registerFarm(@ModelAttribute FarmRequest farmRequest) {
        try{
            return new ResponseEntity<>(farmService.farmResponse(farmRequest), HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/farming-cost")
    public ResponseEntity<FarmingCostDto> calculate(@RequestBody FarmingCostDto dto) {
        FarmingCostDto result = farmingCostService.calculateCosts(dto);
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/farm/{id}/picture")
//    public ResponseEntity<byte[]> getFarmPicture(@PathVariable Long id) {
//        Farm farm = farmRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Farm not found"));
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .body(farm.getFarmPicture());
//    }
}
