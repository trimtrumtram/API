package com.teammanager.employeeHub.service.client;

import com.teammanager.employeeHub.dto.client.ClientResponseDTO;
import com.teammanager.employeeHub.dto.client.CreateClientDTO;
import com.teammanager.employeeHub.dto.client.UpdateClientDTO;
import com.teammanager.employeeHub.model.client.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final List<Client> clients = new ArrayList<>();
    private Long nextClientId = 1L;

    public ClientResponseDTO CreateClient(CreateClientDTO dto) {

        Client client = Client.builder()
                .clientId(nextClientId++)
                .clientFirstName(dto.getClientFirstName())
                .clientLastName(dto.getClientLastName())
                .clientEmail(dto.getClientEmail())
                .clientPhone(dto.getClientPhone())
                .build();

        client.createFromDTO(dto);
        clients.add(client);
        return mapToResponseClientDTO(client);
    }

    public ClientResponseDTO updateClient(Long clientId, UpdateClientDTO dto) {
        Client client = clients.stream()
                .filter(c -> c.getClientId().equals(clientId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Client with id " + clientId + " not found"));

         client.updateFromDTO(dto);

        return mapToResponseClientDTO(client);
    }

    public void deleteClient(Long clientId) {
        Client client = clients.stream()
                .filter(c -> c.getClientId().equals(clientId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Client with id " + clientId + " not found"));
        clients.remove(client);
    }

    public ClientResponseDTO getClientById(long clientId) {
        return clients.stream()
                .filter(c -> c.getClientId().equals(clientId))
                .findFirst()
                .map(this::mapToResponseClientDTO)
                .orElseThrow(() -> new IllegalArgumentException("Client with id " + clientId + " not found"));
    }

    private ClientResponseDTO mapToResponseClientDTO(Client client) {
        return ClientResponseDTO.builder()
                .clientId(client.getClientId())
                .clientFirstName(client.getClientFirstName())
                .clientLastName(client.getClientLastName())
                .clientEmail(client.getClientEmail())
                .clientPhone(client.getClientPhone())
                .build();
    }


}
