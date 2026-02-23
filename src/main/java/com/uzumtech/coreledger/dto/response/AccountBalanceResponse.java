package com.uzumtech.coreledger.dto.response;

import com.uzumtech.coreledger.constant.enums.AccountStatus;
import com.uzumtech.coreledger.constant.enums.Currency;

public record AccountBalanceResponse(Long actualBalance, Currency currency, AccountStatus status) {
}
