package com.crudapi.crud.mapper;

import com.crudapi.crud.dto.client.ClientResponseDTO;
import com.crudapi.crud.dto.client.CreateClientDTO;
import com.crudapi.crud.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientResponseDTO mapToDTO(Client client);

    Client mapToEntity(ClientResponseDTO dto);



}
