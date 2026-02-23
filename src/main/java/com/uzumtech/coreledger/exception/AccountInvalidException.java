package com.uzumtech.coreledger.exception;

import com.uzumtech.coreledger.constant.enums.ErrorCode;

public class AccountInvalidException extends ApplicationException {
    public AccountInvalidException(ErrorCode error) {
        super(error);
    }
}
