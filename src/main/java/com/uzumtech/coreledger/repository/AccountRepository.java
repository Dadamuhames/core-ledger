package com.uzumtech.coreledger.repository;

import com.uzumtech.coreledger.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
