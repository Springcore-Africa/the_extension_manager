package com.oracleous.extention_manager.services.farmService;

import com.oracleous.extention_manager.dto.requests.farmPackage.FarmRequest;
import com.oracleous.extention_manager.dto.response.farmResponse.FarmResponse;

public interface FarmService {
    FarmResponse farmResponse(FarmRequest farmRequest);
}
