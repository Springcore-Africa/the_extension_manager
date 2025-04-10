package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
    boolean existsByEmailAndPassword(String email, String phoneNumber);
    boolean existsByEmail(String email);
}
