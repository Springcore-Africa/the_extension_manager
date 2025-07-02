package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.AgriBusiness;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgriBusinessRepository extends JpaRepository<AgriBusiness, Integer> {

    List<AgriBusiness> findByFarmerId(Long farmerId);

    Optional<AgriBusiness> findByCacNumber(String cacNumber);

    boolean existsByCacNumber(String cacNumber);

    Optional<AgriBusiness> findByFarmer_Users_EmailOrFarmer_PhoneNumber(String email, String phoneNumber);
}
