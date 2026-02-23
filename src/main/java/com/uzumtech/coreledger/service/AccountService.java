package com.uzumtech.coreledger.service;

import com.uzumtech.coreledger.dto.response.AccountBalanceResponse;
import com.uzumtech.coreledger.dto.response.AccountResponse;
import com.uzumtech.coreledger.entity.AccountEntity;

import java.util.UUID;

public interface AccountService {

    AccountBalanceResponse getBalanceByAccountId(Long accountId);

    AccountEntity findByAccountId(Long accountId);

    AccountResponse findByAmsAccountId(UUID amsAccountId);
}
