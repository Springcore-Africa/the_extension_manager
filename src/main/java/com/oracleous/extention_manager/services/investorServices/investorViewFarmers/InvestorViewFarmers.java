package com.oracleous.extention_manager.services.investorServices.investorViewFarmers;

import com.oracleous.extention_manager.dto.response.investorAgriBusinessResponse.InvestorViewAgriBusinessResponse;
import com.oracleous.extention_manager.dto.response.investorFarmerRetrieval.InvestorViewFarmerResponse;

import java.util.List;


public interface InvestorViewFarmers {
    List<InvestorViewFarmerResponse> getAllFarmers();
//    InvestorViewAgriBusinessResponse getAgriBusinessByFarmer(Long farmerId);
    List<InvestorViewAgriBusinessResponse> getAllAgriBusinesses();}
