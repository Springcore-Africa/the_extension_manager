package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FarmersRepository extends JpaRepository<Farmer, Long> {
//    Optional<Farmer> findByEmailIgnoreCase(String email);
//    Optional<Farmer> findByNationalId(String nationalId);
//    Optional<Farmer> findByPhoneNumber(String phoneNumber);

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN TRUE ELSE FALSE END FROM Farmer f WHERE LOWER(f.email) = LOWER(:email) OR f.nationalId = :nationalId OR f.phoneNumber = :phoneNumber")
    boolean existsByEmailOrNationalIdOrPhoneNumber(@Param("email") String email,
                                                   @Param("nationalId") String nationalId,
                                                   @Param("phoneNumber") String phoneNumber);

}
