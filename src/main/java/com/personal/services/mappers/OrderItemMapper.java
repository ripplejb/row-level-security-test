package com.personal.services.mappers;

import com.personal.models.OrderItem;
import com.personal.models.dtos.OrderItemDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderItemMapper {

    @Mapping(source = "purchaseOrder.id", target = "orderId")
    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);
    @InheritInverseConfiguration
    OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto);

}
