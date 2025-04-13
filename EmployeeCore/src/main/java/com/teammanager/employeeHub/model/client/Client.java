package com.teammanager.employeeHub.model.client;


import com.teammanager.employeeHub.dto.client.CreateClientDTO;
import com.teammanager.employeeHub.dto.client.UpdateClientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Client {
    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private String clientEmail;
    private String clientPhone;

    public void updateFromDTO (UpdateClientDTO dto) {
        if(dto.getClientFirstName() != null) {
            clientFirstName = dto.getClientFirstName();
        }
        if(dto.getClientLastName() != null) {
            clientLastName = dto.getClientLastName();
        }
        if(dto.getClientEmail() != null) {
            clientEmail = dto.getClientEmail();
        }
        if(dto.getClientPhone() != null) {
            if(isValidPhoneNumber(dto.getClientPhone())) {
                clientPhone = dto.getClientPhone();
            }
        }
    }

    public void createFromDTO (CreateClientDTO dto) {
        if(clientEmail.equals(dto.getClientEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if(clientPhone.equals(dto.getClientPhone())) {
            throw new IllegalArgumentException("Phone already exists");
        } else if (!isValidPhoneNumber(dto.getClientPhone())) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    private boolean isValidPhoneNumber(String clientPhone) {
        return clientPhone.matches("^\\+?[0-9\\s\\-()]{10,15}$");
    }
}
