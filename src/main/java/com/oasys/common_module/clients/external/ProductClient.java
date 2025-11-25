// java
package com.oasys.common_module.clients.external;


import com.oasys.common_module.clients.config.FeignCommonConfig;
import com.oasys.common_module.clients.external.model.ProductRequest;
import com.oasys.common_module.clients.external.model.ProductResponse;
import com.oasys.common_module.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CircuitBreaker(name = "productServiceCircuitBreaker", fallbackMethod = "productServiceFallback")
@FeignClient(name = "PRODUCT-SERVICE", path = "/product", configuration = FeignCommonConfig.class)
public interface ProductClient {

    @PostMapping
    ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest);

    @GetMapping("/{id}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId);

    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity);


    // Fallbacks for circuit breaker (overloaded to match each method signature + Throwable)

    default void productServiceFallback(Exception exception) {
      throw new CustomException("Product Service is currently unavailable. Please try again later."
      , "SERVICE UNAVAILABLE", 500);
    }


}
