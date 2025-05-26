package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.LocalAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalAdminRepository extends JpaRepository<LocalAdmin, Long> {

    boolean existsByUsersEmailAndPhoneNumber(String email, String phoneNumber);
}
