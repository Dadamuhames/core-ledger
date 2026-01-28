package com.uzumtech.coreledger.repository;

import com.uzumtech.coreledger.entity.LedgerEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerEntryRepository extends JpaRepository<LedgerEntryEntity, Long> {
}
