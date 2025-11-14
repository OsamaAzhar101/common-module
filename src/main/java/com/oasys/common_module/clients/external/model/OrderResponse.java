package com.oasys.common_module.clients.external.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private long orderId;

    private Instant orderDate;
    private long amount;

    private String orderStatus;

    private ProductResponse productDetails;

    private PaymentResponse paymentDetails;

}
