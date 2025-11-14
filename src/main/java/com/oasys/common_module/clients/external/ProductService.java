// java
package com.oasys.common_module.clients.external;


import com.oasys.common_module.clients.config.FeignCommonConfig;
import com.oasys.common_module.clients.external.model.ProductRequest;
import com.oasys.common_module.clients.external.model.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PRODUCT-SERVICE", path = "/product", configuration = FeignCommonConfig.class)
public interface ProductService {

    @PostMapping
    ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest);

    @GetMapping("/{id}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId);

    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity);
}
