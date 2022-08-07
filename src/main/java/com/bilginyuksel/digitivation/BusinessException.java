package com.bilginyuksel.digitivation;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final int errorCode;
    private final String errorMessage;
    private Exception exception;

    public BusinessException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BusinessException(int errorCode, String errorMessage, Exception ex) {
        this(errorCode, errorMessage);

        this.exception = ex;
    }
}
