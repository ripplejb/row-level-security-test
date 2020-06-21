package com.personal.controllers;

import com.personal.models.dtos.OrderDto;
import com.personal.services.order.OrderService;
import com.personal.services.security.rules.SecurityPolicy;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

import javax.inject.Inject;

@Controller("/orders")
public class OrderController {

    @Inject
    private OrderService orderService;

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @SecurityPolicy("order-full")
    Single<HttpResponse<OrderDto>> Post(OrderDto orderDto) {
        return orderService.createNewOrder(orderDto)
                .map(order -> HttpResponse.ok(order));
    }

}
