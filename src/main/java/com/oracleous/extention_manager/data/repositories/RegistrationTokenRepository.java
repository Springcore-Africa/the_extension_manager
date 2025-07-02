package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.RegistrationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, String> {
    Optional<RegistrationToken> findByToken(String token);
}