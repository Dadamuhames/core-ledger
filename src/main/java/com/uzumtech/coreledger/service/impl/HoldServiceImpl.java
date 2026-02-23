package com.uzumtech.coreledger.service.impl;

import com.uzumtech.coreledger.service.HoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HoldServiceImpl implements HoldService {

    @Override
    public void createHold() {

    }

    @Override
    public void confirmHold(Long transactionId) {

    }

    @Override
    public void releaseHold(Long transactionId) {

    }
}
