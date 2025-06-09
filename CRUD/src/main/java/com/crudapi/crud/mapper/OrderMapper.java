package com.crudapi.crud.mapper;

import com.crudapi.crud.dto.order.CreateOrderDTO;
import com.crudapi.crud.dto.order.OrderResponseDTO;
import com.crudapi.crud.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(source = "client", target = "id")
    OrderResponseDTO mapToDTO(Order order);

    @Mapping(source = "client", target = "id")
    Order mapToEntity(CreateOrderDTO dto);
}
