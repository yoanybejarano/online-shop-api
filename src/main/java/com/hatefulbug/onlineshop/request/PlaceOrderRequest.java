package com.hatefulbug.onlineshop.request;

import lombok.Data;

@Data
public class PlaceOrderRequest {
    private int userId;
    private int paymentMethodId;
    private String shippingAddress;
}
