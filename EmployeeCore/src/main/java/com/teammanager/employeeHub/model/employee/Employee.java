package com.teammanager.employeeHub.model.employee;

import com.teammanager.employeeHub.dto.employee.CreateEmployeeDTO;
import com.teammanager.employeeHub.dto.employee.UpdateEmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    public void createFromDTO(UpdateEmployeeDTO dto) {
        if(dto.getFirstName() != null) {
            firstName = dto.getFirstName();
        }
        if(dto.getLastName() != null) {
            lastName = dto.getLastName();
        }
        if(dto.getEmail() != null) {
            if(email.equals(dto.getEmail()) && !id.equals(dto.getId())) {
                throw new IllegalArgumentException("Email already exists");
            }
            email = dto.getEmail();
        }
        if(dto.getPassword() != null) {
            password = dto.getPassword();
        }
        if(dto.getRole() != null) {
            role = dto.getRole();
        }
    }

    public void createFromDTO(CreateEmployeeDTO dto) {
        if(email.equals(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if(dto.getRole() == null || !(dto.getRole() == Role.MANAGER || dto.getRole() == Role.ADMIN)) {
            throw new IllegalArgumentException("Role must be MANAGER or ADMIN");
        }
    }
}
