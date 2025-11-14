// java
package com.oasys.common_module.clients.error;

public class RemoteServiceException extends RuntimeException {
    private final String code;
    private final int httpStatus;

    public RemoteServiceException(String message, String code, int httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
