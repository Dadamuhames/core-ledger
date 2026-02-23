package com.uzumtech.coreledger.controller;

import com.uzumtech.coreledger.dto.response.AccountBalanceResponse;
import com.uzumtech.coreledger.dto.response.AccountResponse;
import com.uzumtech.coreledger.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/core-ledger/accounts/")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/by-id/{accountId}/balance")
    public ResponseEntity<AccountBalanceResponse> getById(@PathVariable Long accountId) {

        AccountBalanceResponse response = accountService.getBalanceByAccountId(accountId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-ams-id/{amsAccountId}")
    public ResponseEntity<AccountResponse> getByAmsAccountId(@PathVariable UUID amsAccountId) {

        AccountResponse response = accountService.findByAmsAccountId(amsAccountId);

        return ResponseEntity.ok(response);
    }
}
