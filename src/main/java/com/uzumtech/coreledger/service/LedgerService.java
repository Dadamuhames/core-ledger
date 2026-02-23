package com.uzumtech.coreledger.service;

import com.uzumtech.coreledger.dto.event.TransactionEvent;

public interface LedgerService {

    void performTransaction(TransactionEvent event);

}
