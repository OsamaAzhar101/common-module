// java
package com.oasys.common_module.clients.external;


import com.oasys.common_module.clients.config.FeignCommonConfig;
import com.oasys.common_module.clients.external.model.PaymentRequest;
import com.oasys.common_module.clients.external.model.PaymentResponse;
import com.oasys.common_module.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CircuitBreaker(name = "paymentServiceCircuitBreaker", fallbackMethod = "paymentServiceFallback")
@FeignClient(name = "PAYMENT-SERVICE", path = "/payment", configuration = FeignCommonConfig.class)
public interface PaymentClient {

    @PostMapping("/process")
    ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping("/response/{orderId}")
    ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable("orderId") long orderId);

    default void paymentServiceFallback(Exception exception) {
        throw new CustomException("Payment Service is currently unavailable. Please try again later."
                , "SERVICE UNAVAILABLE", 500);
    }
}
