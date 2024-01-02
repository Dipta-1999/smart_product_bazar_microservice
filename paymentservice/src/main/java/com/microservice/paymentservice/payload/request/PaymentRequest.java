package com.microservice.paymentservice.payload.request;

import com.microservice.paymentservice.utils.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {
    private int orderId;
    private int amount;
    private String referenceNumber;
    private PaymentMode paymentMode;
}
