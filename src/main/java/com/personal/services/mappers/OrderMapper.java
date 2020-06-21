package com.personal.services.mappers;

import com.personal.models.PurchaseOrder;
import com.personal.models.dtos.OrderDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "userCredential.id", target = "userId")
    OrderDto orderToOrderDto(PurchaseOrder purchaseOrder);
    @InheritInverseConfiguration
    PurchaseOrder orderDtoToOrder(OrderDto orderDto);
}
