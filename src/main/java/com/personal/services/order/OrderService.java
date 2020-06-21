package com.personal.services.order;

import com.personal.models.dtos.OrderDto;
import com.personal.models.search.requests.OrderSearchRequest;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.UUID;

public interface OrderService {
    Single<OrderDto> createNewOrder(OrderDto orderDto);
    Single<OrderDto> updateOrder(OrderDto orderDto);
    Maybe<OrderDto> getOrderById(UUID orderId);
    Flowable<OrderDto> searchOrder(OrderSearchRequest orderSearchRequest);
}
