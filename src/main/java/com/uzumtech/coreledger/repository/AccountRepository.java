package com.uzumtech.coreledger.repository;

import com.uzumtech.coreledger.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByAmsAccountId(UUID amsAccountId);

}
