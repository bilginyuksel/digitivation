package com.bilginyuksel.digitivation;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    private Exception exception;

    public BusinessException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BusinessException(String errorCode, String errorMessage, Exception ex) {
        this(errorCode, errorMessage);

        this.exception = ex;
    }
}
