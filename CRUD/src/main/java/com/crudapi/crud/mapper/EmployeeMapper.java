package com.crudapi.crud.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper<D, E> {

    E mapToEntity(D dto);
    D mapToDTO(E employee);
}
