package com.uzumtech.coreledger.service.impl;

import com.uzumtech.coreledger.constant.enums.ErrorCode;
import com.uzumtech.coreledger.dto.response.AccountBalanceResponse;
import com.uzumtech.coreledger.dto.response.AccountResponse;
import com.uzumtech.coreledger.entity.AccountEntity;
import com.uzumtech.coreledger.exception.AccountNotFoundException;
import com.uzumtech.coreledger.mapper.AccountMapper;
import com.uzumtech.coreledger.repository.AccountRepository;
import com.uzumtech.coreledger.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    @Transactional(readOnly = true)
    public AccountBalanceResponse getBalanceByAccountId(Long accountId) {
        AccountEntity account = findByAccountId(accountId);

        return accountMapper.entityToBalanceResponse(account);
    }

    @Override
    public AccountEntity findByAccountId(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponse findByAmsAccountId(UUID amsAccountId) {
        AccountEntity account = accountRepository.findByAmsAccountId(amsAccountId).orElseThrow(() -> new AccountNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND));

        return accountMapper.entityToResponse(account);
    }
}
