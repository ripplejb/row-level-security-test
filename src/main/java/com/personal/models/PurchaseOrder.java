package com.personal.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userCredentialId")
    private UserCredential userCredential;
    private String shipping;
    private String payment;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(UserCredential userCredential) {
        this.userCredential = userCredential;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shippingInfo) {
        this.shipping = shippingInfo;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String paymentInfo) {
        this.payment = paymentInfo;
    }

}
