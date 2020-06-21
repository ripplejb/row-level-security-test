package com.personal.dao;

import com.personal.models.OrderItem;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.RxJavaCrudRepository;

import java.util.UUID;

@Repository
public interface OrderItemRepository extends RxJavaCrudRepository<OrderItem, UUID> {
}
