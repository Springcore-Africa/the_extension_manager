package com.oracleous.extention_manager.data.repositories;

import com.oracleous.extention_manager.data.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

}
