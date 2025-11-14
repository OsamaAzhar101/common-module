// java
package com.oasys.common_module.clients.external;


import com.oasys.common_module.clients.config.FeignCommonConfig;
import com.oasys.common_module.clients.external.model.PaymentRequest;
import com.oasys.common_module.clients.external.model.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PAYMENT-SERVICE", path = "/payment", configuration = FeignCommonConfig.class)
public interface PaymentService {

    @PostMapping("/process")
    ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping("/response/{orderId}")
    ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable("orderId") long orderId);
}
