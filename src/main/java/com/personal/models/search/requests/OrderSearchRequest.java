package com.personal.models.search.requests;

public class OrderSearchRequest {

    private String shippingInfoContains;
    private String paymentInfoContains;

    public String getShippingInfoContains() {
        return shippingInfoContains;
    }

    public void setShippingInfoContains(String shippingInfoContains) {
        this.shippingInfoContains = shippingInfoContains;
    }

    public String getPaymentInfoContains() {
        return paymentInfoContains;
    }

    public void setPaymentInfoContains(String paymentInfoContains) {
        this.paymentInfoContains = paymentInfoContains;
    }
}
