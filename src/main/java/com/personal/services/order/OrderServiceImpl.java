package com.personal.services.order;

import com.personal.dao.PurchaseOrderRepository;
import com.personal.models.dtos.OrderDto;
import com.personal.models.search.requests.OrderSearchRequest;
import com.personal.services.mappers.OrderMapper;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class OrderServiceImpl implements OrderService {

    @Inject
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public Single<OrderDto> createNewOrder(OrderDto orderDto) {
        return purchaseOrderRepository
                .save(OrderMapper.INSTANCE.orderDtoToOrder(orderDto))
                .map(OrderMapper.INSTANCE::orderToOrderDto);
    }

    @Override
    public Single<OrderDto> updateOrder(OrderDto orderDto) {
        return purchaseOrderRepository
                .save(OrderMapper.INSTANCE.orderDtoToOrder(orderDto))
                .map(OrderMapper.INSTANCE::orderToOrderDto);
    }

    @Override
    public Maybe<OrderDto> getOrderById(UUID orderId) {
        return purchaseOrderRepository.findById(orderId)
                .map(OrderMapper.INSTANCE::orderToOrderDto);
    }

    @Override
    public Flowable<OrderDto> searchOrder(OrderSearchRequest orderSearchRequest) {
        if (orderSearchRequest.getPaymentInfoContains().isEmpty() &&
                orderSearchRequest.getShippingInfoContains().isEmpty()) {
            return Flowable.empty();
        }
        if (!orderSearchRequest.getPaymentInfoContains().isEmpty() &&
                !orderSearchRequest.getShippingInfoContains().isEmpty()) {
            return purchaseOrderRepository
                    .findByShippingLikeAndPaymentLike(
                            "%" + orderSearchRequest.getShippingInfoContains() + "%",
                            "%" + orderSearchRequest.getPaymentInfoContains() + "%"
                    ).map(OrderMapper.INSTANCE::orderToOrderDto);
        }

        if (!orderSearchRequest.getPaymentInfoContains().isEmpty()) {
            return purchaseOrderRepository
                    .findByPaymentLike(
                            "%" + orderSearchRequest.getPaymentInfoContains() + "%"
                    ).map(OrderMapper.INSTANCE::orderToOrderDto);
        }
        return purchaseOrderRepository
                .findByShippingLike(
                        "%" + orderSearchRequest.getShippingInfoContains() + "%"
                ).map(OrderMapper.INSTANCE::orderToOrderDto);
    }
}
