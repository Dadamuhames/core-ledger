package com.uzumtech.coreledger.controller;

import com.uzumtech.coreledger.dto.request.ValidationRequest;
import com.uzumtech.coreledger.dto.response.AccountBalanceResponse;
import com.uzumtech.coreledger.dto.response.AccountResponse;
import com.uzumtech.coreledger.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

        AccountResponse response = accountService.getByAmsAccountId(amsAccountId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/validation")
    public ResponseEntity<Void> validateAmountAndCurrency(@Valid @RequestBody ValidationRequest request) {

        accountService.validateBalanceByAccountId(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
