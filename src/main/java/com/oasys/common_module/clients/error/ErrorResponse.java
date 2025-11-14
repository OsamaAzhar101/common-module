// java
package com.oasys.common_module.clients.error;

import lombok.Data;

@Data
public class ErrorResponse {
    private String errorMessage;
    private String errorCode;
}
