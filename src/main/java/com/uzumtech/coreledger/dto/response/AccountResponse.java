package com.uzumtech.coreledger.dto.response;

import com.uzumtech.coreledger.constant.enums.AccountStatus;

public record AccountResponse(Long ledgerAccountId, AccountStatus status) {
}
