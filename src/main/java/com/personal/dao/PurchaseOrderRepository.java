package com.personal.dao;

import com.personal.models.PurchaseOrder;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.RxJavaCrudRepository;

import java.util.UUID;

@Repository
public interface PurchaseOrderRepository extends RxJavaCrudRepository<PurchaseOrder, UUID> {
}
