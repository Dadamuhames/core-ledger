package com.uzumtech.coreledger.exception;

import com.uzumtech.coreledger.constant.enums.ErrorCode;
import com.uzumtech.coreledger.constant.enums.ErrorType;
import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends ApplicationException {
    public AccountNotFoundException(ErrorCode error) {
        super(error.getCode(), error.getMessage(), ErrorType.VALIDATION, HttpStatus.NOT_FOUND);
    }
}
