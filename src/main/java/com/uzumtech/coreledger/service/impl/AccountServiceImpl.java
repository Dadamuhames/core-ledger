package com.uzumtech.coreledger.service.impl;

import com.uzumtech.coreledger.constant.enums.ErrorCode;
import com.uzumtech.coreledger.dto.request.ValidationRequest;
import com.uzumtech.coreledger.dto.response.AccountBalanceResponse;
import com.uzumtech.coreledger.dto.response.AccountResponse;
import com.uzumtech.coreledger.entity.AccountEntity;
import com.uzumtech.coreledger.exception.AccountInvalidException;
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
        return accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND_CODE));
    }

    @Override
    public AccountEntity findByAmsAccountId(UUID amsAccountId) {
        return accountRepository.findByAmsAccountId(amsAccountId).orElseThrow(() -> new AccountNotFoundException(ErrorCode.ACCOUNT_NOT_FOUND_CODE));
    }


    @Override
    @Transactional(readOnly = true)
    public AccountResponse getByAmsAccountId(UUID amsAccountId) {
        AccountEntity account = findByAmsAccountId(amsAccountId);


        return accountMapper.entityToResponse(account);
    }

    @Override
    @Transactional(readOnly = true)
    public void validateBalanceByAccountId(ValidationRequest request) {
        AccountEntity account = findByAmsAccountId(request.amsAccountId());

        if (account.getActualBalance() < request.amount() || account.getCurrency() != request.currency()) {
            throw new AccountInvalidException(ErrorCode.ACCOUNT_AMOUNT_OR_CURRENCY_INVALID_CODE);
        }
    }
}
