package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.ExtensionWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExtensionWorkerRepository extends JpaRepository<ExtensionWorker, Long> {
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END FROM ExtensionWorker f WHERE LOWER(f.users.email) = LOWER(:email) OR f.phoneNumber = :phoneNumber")
    boolean existsByUsersEmailAndPhoneNumber(String email, String phoneNumber);

//    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END FROM ExtensionWorker f WHERE LOWER(f.users.email) = LOWER(:email) OR f.phoneNumber = :phoneNumber")
//    boolean existsByUsersEmailAndPhoneNumber(String phoneNumber, String email);

    Optional<ExtensionWorker> findByUsersEmail(String email);
}
