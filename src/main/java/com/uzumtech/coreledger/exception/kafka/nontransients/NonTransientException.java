package com.uzumtech.coreledger.exception.kafka.nontransients;

import com.uzumtech.coreledger.constant.enums.ErrorCode;
import com.uzumtech.coreledger.constant.enums.ErrorType;
import com.uzumtech.coreledger.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class NonTransientException extends ApplicationException {

    public NonTransientException(Exception ex) {
        super(ErrorCode.INTERNAL_SERVICE_ERROR_CODE.getCode(), ex.getMessage(), ErrorType.INTERNAL, null);
    }


    public NonTransientException(int code, String message, ErrorType errorType, HttpStatus status) {
        super(code, message, errorType, status);
    }
}
