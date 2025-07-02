package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.FarmFinance;
import com.oracleous.extention_manager.data.model.FarmingCost;
import com.oracleous.extention_manager.data.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FarmingCostRepository extends JpaRepository<FarmingCost, Long> {
//    Optional<FarmingCost> findById(Long farmingCost);
    Optional<FarmingCost> findTopByUserOrderByIdDesc(Users user);
}
