package com.teammanager.employeeHub.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
@AllArgsConstructor
public class CreateClientDTO {

    @NotBlank(message = "First name is required")
    private String clientFirstName;

    @NotBlank(message = "Last name is required")
    private String clientLastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String clientEmail;

    @NotBlank(message = "Phone number is required")
    private String clientPhone;
}
