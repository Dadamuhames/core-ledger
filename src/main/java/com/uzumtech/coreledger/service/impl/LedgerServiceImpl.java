package com.uzumtech.coreledger.service.impl;

import com.uzumtech.coreledger.constant.Constants;
import com.uzumtech.coreledger.dto.event.TransactionEvent;
import com.uzumtech.coreledger.entity.AccountEntity;
import com.uzumtech.coreledger.entity.LedgerEntryEntity;
import com.uzumtech.coreledger.mapper.LedgerMapper;
import com.uzumtech.coreledger.repository.AccountRepository;
import com.uzumtech.coreledger.repository.LedgerEntryRepository;
import com.uzumtech.coreledger.service.AccountService;
import com.uzumtech.coreledger.service.HoldService;
import com.uzumtech.coreledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LedgerServiceImpl implements LedgerService {

    private final LedgerEntryRepository ledgerEntryRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final LedgerMapper ledgerMapper;
    private final HoldService holdService;

    @Override
    @Transactional
    public void performTransaction(TransactionEvent event) {

        AccountEntity senderAccount = accountService.findByAccountId(event.senderAccountId());
        LedgerEntryEntity ledgerDebitEntity = ledgerMapper.eventToDebitEntity(event, senderAccount);

        AccountEntity receiverAccount = accountService.findByAccountId(event.receiverAccountId());
        LedgerEntryEntity ledgerCreditEntity = ledgerMapper.eventToCreditEntity(event, receiverAccount);

        AccountEntity bankAccount = accountService.findByAccountId(Constants.BANK_ACCOUNT_ID);
        LedgerEntryEntity ledgerFeeEntity = ledgerMapper.eventToFeeEntity(event, bankAccount);

        ledgerEntryRepository.saveAll(List.of(ledgerDebitEntity, ledgerCreditEntity, ledgerFeeEntity));

        // update accounts
        Long senderNewBalance = senderAccount.getActualBalance() - event.amount() - event.fee();
        senderAccount.setActualBalance(senderNewBalance);

        Long receiverNewBalance = receiverAccount.getActualBalance() + event.amount();
        receiverAccount.setActualBalance(receiverNewBalance);

        Long bankNewBalance = bankAccount.getActualBalance() + event.fee();
        bankAccount.setActualBalance(bankNewBalance);

        accountRepository.saveAll(List.of(senderAccount, receiverAccount, bankAccount));

        // confirm hold
        holdService.confirmHold(event.transactionId());
    }
}
