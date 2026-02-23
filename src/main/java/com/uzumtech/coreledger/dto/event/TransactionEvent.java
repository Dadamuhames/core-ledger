package com.uzumtech.coreledger.dto.event;

import com.uzumtech.coreledger.constant.enums.Currency;
import com.uzumtech.coreledger.constant.enums.TransactionType;
import jakarta.validation.constraints.NotNull;

public record TransactionEvent(@NotNull Long transactionId, @NotNull Long senderAccountId,
                               @NotNull Long receiverAccountId, @NotNull TransactionType transactionType,
                               @NotNull Long amount, @NotNull Long fee,
                               @NotNull Currency currency) {
}
