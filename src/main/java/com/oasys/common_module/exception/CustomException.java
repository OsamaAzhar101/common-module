package com.oasys.common_module.exception;

import lombok.Builder;
import lombok.Data;

@Data

public class CustomException extends RuntimeException {
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    private String errorMessage;
    private String errorCode;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    private int statusCode;
//    private int statusCode;

    public CustomException(String errorMessage, String errorCode
            , int statusCode
    ) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }


}


