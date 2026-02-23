package com.uzumtech.coreledger.service;

public interface HoldService {

    void createHold();

    void confirmHold(Long transactionId);

    void releaseHold(Long transactionId);
}
