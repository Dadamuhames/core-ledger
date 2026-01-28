package com.uzumtech.coreledger.repository;

import com.uzumtech.coreledger.entity.HoldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoldRepository extends JpaRepository<HoldEntity, Long> {
}
