package com.oasys.common_module.clients.external.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    private long productId;
    private int quantity;
    private long price;

    private com.oasys.common_module.clients.external.model.PaymentMode paymentMode;


}
