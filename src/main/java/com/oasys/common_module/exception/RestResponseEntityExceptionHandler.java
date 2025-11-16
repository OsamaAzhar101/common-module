package com.oasys.common_module.exception;




import com.oasys.common_module.clients.error.ErrorResponse;
import com.oasys.common_module.clients.error.RemoteServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
        ErrorResponse body = new ErrorResponse();
        body.setErrorMessage(exception.getMessage());
        body.setErrorCode(exception.getErrorCode());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RemoteServiceException.class)
    public ResponseEntity<ErrorResponse> handleRemoteService(RemoteServiceException ex) {
        ErrorResponse body = new ErrorResponse();
        body.setErrorMessage(ex.getMessage());
        body.setErrorCode(ex.getCode());
        return ResponseEntity.status(ex.getHttpStatus()).body(body);
    }

}
