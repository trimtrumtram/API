package com.teammanager.employeeHub.dto.employee;

import com.teammanager.employeeHub.model.employee.Role;
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
