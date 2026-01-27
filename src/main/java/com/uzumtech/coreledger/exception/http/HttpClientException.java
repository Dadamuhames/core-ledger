package com.uzumtech.coreledger.exception.http;

import com.uzumtech.coreledger.constant.enums.ErrorCode;
import com.uzumtech.coreledger.constant.enums.ErrorType;
import com.uzumtech.coreledger.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class HttpClientException extends ApplicationException {

    public HttpClientException(String message, HttpStatusCode status) {
        super(ErrorCode.HTTP_CLIENT_ERROR_CODE.getCode(), message, ErrorType.EXTERNAL, HttpStatus.valueOf(status.value()));
    }
}
