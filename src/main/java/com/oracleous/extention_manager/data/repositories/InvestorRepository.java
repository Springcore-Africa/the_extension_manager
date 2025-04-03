package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {
    boolean existsByEmailAndPhoneNumber(String email, String phoneNumber);
}
