package com.oracleous.extention_manager.services.farmersServices.AdminReadFarmers;


import com.oracleous.extention_manager.dto.requests.adminViewFarmer.AdminViewFarmersRequest;
import com.oracleous.extention_manager.dto.response.adminToViewFarmers.AdminViewFarmersResponse;

public interface AdminToReadAllFarmer {
    AdminViewFarmersResponse adminViewFarmers(AdminViewFarmersRequest adminViewFarmersRequest);
}
