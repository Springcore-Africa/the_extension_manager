package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRegistrationRepository extends JpaRepository<Admin, Long> {
}
