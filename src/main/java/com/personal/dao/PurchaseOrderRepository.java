package com.personal.dao;

import com.personal.models.PurchaseOrder;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.RxJavaCrudRepository;
import io.reactivex.Flowable;

import java.util.UUID;

@Repository
public interface PurchaseOrderRepository extends RxJavaCrudRepository<PurchaseOrder, UUID> {
    Flowable<PurchaseOrder> findByShippingLike(String shippingInfoSubstring);
    Flowable<PurchaseOrder> findByPaymentLike(String paymentInfoSubstring);
    Flowable<PurchaseOrder> findByShippingLikeAndPaymentLike(String shippingInfoSubstring,
                                                                     String paymentInfoSubstring);
}
