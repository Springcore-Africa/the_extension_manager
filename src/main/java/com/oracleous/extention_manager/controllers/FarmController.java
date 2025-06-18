package com.oracleous.extention_manager.controllers;

import com.oracleous.extention_manager.data.model.Farm;
import com.oracleous.extention_manager.data.repositories.FarmRepository;
import com.oracleous.extention_manager.dto.requests.farmPackage.FarmRequest;
import com.oracleous.extention_manager.services.farmService.FarmService;
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
    private final FarmRepository farmRepository;

    @PostMapping("/farm_registration")
    public ResponseEntity<?> registerFarm(@ModelAttribute FarmRequest farmRequest) {
        try{
            return new ResponseEntity<>(farmService.farmResponse(farmRequest), HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/farm/{id}/picture")
    public ResponseEntity<byte[]> getFarmPicture(@PathVariable Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found"));

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(farm.getFarmPicture());
    }
}
