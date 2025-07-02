package com.oracleous.extention_manager.services.farmService;

import com.oracleous.extention_manager.dto.requests.farmPackage.FarmFinanceRequestDto;
import com.oracleous.extention_manager.dto.response.farmResponse.FarmFinanceResponseDto;

public interface FarmFinanceService {
    FarmFinanceResponseDto calculateFarmFinance(FarmFinanceRequestDto farmFinanceRequestDto);
}
