package com.uzumtech.coreledger.dto.event;

import com.uzumtech.coreledger.constant.enums.TransactionStatus;

public record TransactionResultEvent(Long transactionId, TransactionStatus status, String errorMessage) {

    public static TransactionResultEvent of(Long transactionId, TransactionStatus status) {
        return new TransactionResultEvent(transactionId, status, null);
    }

}
