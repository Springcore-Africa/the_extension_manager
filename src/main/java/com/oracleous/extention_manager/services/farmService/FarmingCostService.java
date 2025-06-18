package com.oracleous.extention_manager.services.farmService;


import com.oracleous.extention_manager.dto.requests.farmPackage.FarmingCostDto;

public interface FarmingCostService {
    FarmingCostDto calculateCosts(FarmingCostDto dto);
}
