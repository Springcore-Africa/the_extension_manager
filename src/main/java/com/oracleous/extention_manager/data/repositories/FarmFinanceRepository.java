package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.FarmFinance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmFinanceRepository extends JpaRepository<FarmFinance , Long> {
}
