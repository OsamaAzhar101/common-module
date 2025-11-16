// java
package com.oasys.common_module.clients.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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
            byte[] bytes = in.readAllBytes();
            String raw = new String(bytes, StandardCharsets.UTF_8);

            // Try parsing expected ErrorResponse
            try {
                ErrorResponse payload = mapper.readValue(raw, ErrorResponse.class);
                return new RemoteServiceException(payload.getErrorMessage(), payload.getErrorCode(), response.status());
            } catch (IOException parseEx) {
                log.warn("Could not parse error body as ErrorResponse. rawBody={}", raw);
                // Fallback: return a RemoteServiceException containing raw body and PARSE_ERROR code
                String msg = "Failed to parse error response: " + (raw.length() > 1024 ? raw.substring(0, 1024) + "..." : raw);
                return new RemoteServiceException(msg, "PARSE_ERROR", response.status());
            }
        } catch (IOException ex) {
            log.error("Error reading feign response body", ex);
            return new RemoteServiceException("Failed to read error body", "READ_ERROR", response.status());
        }
    }
}
