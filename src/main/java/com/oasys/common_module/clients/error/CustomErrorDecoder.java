// java
package com.oasys.common_module.clients.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("Feign error: method={}, status={}", methodKey, response.status());
        try (InputStream in = response.body() != null ? response.body().asInputStream() : null) {
            if (in == null) {
                return new RemoteServiceException("Empty error body", "EMPTY_BODY", response.status());
            }
            ErrorResponse payload = mapper.readValue(in, ErrorResponse.class);
            return new RemoteServiceException(payload.getErrorMessage(), payload.getErrorCode(), response.status());
        } catch (IOException ex) {
            return new RemoteServiceException("Failed to parse error response", "PARSE_ERROR", response.status());
        }
    }
}
