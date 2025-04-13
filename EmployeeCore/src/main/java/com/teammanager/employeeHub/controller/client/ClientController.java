package com.teammanager.employeeHub.controller.client;

import com.teammanager.employeeHub.dto.client.ClientResponseDTO;
import com.teammanager.employeeHub.dto.client.CreateClientDTO;
import com.teammanager.employeeHub.dto.client.UpdateClientDTO;
import com.teammanager.employeeHub.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/client")
    public ClientResponseDTO createClient(@RequestBody CreateClientDTO clientDTO) {
        return clientService.CreateClient(clientDTO);
    }

    @PutMapping("/client/{id}")
    public ClientResponseDTO updateClient(@PathVariable Long id, @RequestBody UpdateClientDTO clientDTO) {
        return clientService.updateClient(id, clientDTO);
    }

    @DeleteMapping("/client/{id}")
    public boolean deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return true;
    }

    @GetMapping("/client/{id}")
    public ClientResponseDTO getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }
}
