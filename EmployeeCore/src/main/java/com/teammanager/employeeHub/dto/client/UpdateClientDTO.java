package com.teammanager.employeeHub.dto.client;

import lombok.Data;

@Data
public class UpdateClientDTO  {

    private Long clientId;
    private String clientFirstName;
    private String clientLastName;
    private String clientEmail;
    private String clientPhone;
}
