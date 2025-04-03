package com.teammanager.employeeHub.dto;

import com.teammanager.employeeHub.model.Role;
import lombok.Data;

@Data
public class UpdateEmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

}
