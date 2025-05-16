package com.oracleous.extention_manager.services.investorServices.investorViewFarmers;

import com.oracleous.extention_manager.dto.response.investorFarmerRetrieval.InvestorViewFarmerResponse;

import java.util.List;


public interface InvestorViewFarmers {
    List<InvestorViewFarmerResponse> getAllFarmers();
}
