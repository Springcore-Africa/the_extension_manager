package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, String> {

    boolean existsByUsers_Email(String email);
}