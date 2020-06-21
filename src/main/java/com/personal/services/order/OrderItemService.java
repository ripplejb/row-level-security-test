package com.personal.services.order;

import com.personal.models.dtos.OrderItemDto;
import com.personal.models.search.requests.OrderItemSearchRequest;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;
import java.util.UUID;

public interface OrderItemService {
    Single<OrderItemDto> createOrderItemDto(OrderItemDto orderItemDto);
    Single<OrderItemDto> updateOrderItemDto(OrderItemDto orderItemDto);
    Maybe<OrderItemDto> orderItemById(UUID orderItemId);
    Flowable<List<OrderItemDto>> searchOrderItem(OrderItemSearchRequest orderItemSearchRequest);
}
